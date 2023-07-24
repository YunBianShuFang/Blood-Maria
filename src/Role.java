import java.util.Random;

public class Role {
    //职业
    private String ID;
    //血量
    private int health;
    private int healthPlus;
    //体力上限
    private int staminaplus;
    //体力
    private int stamina;
    //特殊能力
    private String ability;
    private double buff;
    Random random = new Random();

    public Role(String ID, int health, int healthPlus, int staminaplus, int stamina, String ability, double buff) {
        this.ID = ID;
        this.health = health;
        this.healthPlus = healthPlus;
        this.staminaplus = staminaplus;
        this.stamina = stamina;
        this.ability = ability;
        this.buff = buff;
    }
    public void ViewSelf(Role role,Resources resources,Boat boat){
        System.out.println("您当前的血量为:"+role.getHealth()+"/"+role.getHealthPlus());
        System.out.println("您当前的体力为:"+role.getStamina()+"/"+role.getStaminaplus());
        System.out.println("血色玛丽亚号当前的耐久值为"+boat.getDurable()+"/200");
        System.out.println("您麾下的船员数量为"+boat.getCrew()+"/30");
        System.out.println("您当前航行的里程数为"+boat.getMiles()+"/150");
        System.out.println("您目前拥有"+resources.getWood()+"根木头"+resources.getFood()+"份食物"+resources.getWater()+"份水"+resources.getMedical()+"份医疗资源");
    }

    public void Moving(Role role,Resources resources,Boat boat){
        //剩余体力
        int remainStamina = role.getStamina();
        //剩余的木头资源
        int remainWood = resources.getWood();
        //当前里程数
        int CurrentMiles = boat.getMiles();
        int Num;
        if (remainStamina >= 1 && remainWood>=5){
            if(role.getID().equals("领航员")){
                Num = random.nextInt(10)+5;
            } else {
                Num = random.nextInt(7)+3;
            }
            //消耗体力和木头
            remainStamina = remainStamina - 1;
            remainWood = remainWood -5;
            //里程增加
            CurrentMiles = CurrentMiles+Num;
            //同步数据
            role.setStamina(remainStamina);
            resources.setWood(remainWood);
            boat.setMiles(CurrentMiles);
            //信息提示
            System.out.println("血色玛丽亚号前进了"+Num+"里程");
            System.out.println("剩余体力 "+role.getStamina()+"点");
        } else if(remainWood<5) {
            System.out.println("木头资源不够,需要五根木头,无法前进,请选择其他行动");
        } else {
            System.out.println("体力不够,请选择其他行动");
        }
    }
    public void GetCrew(Role role,Boat boat){
        //剩余体力
        int remainStamina = role.getStamina();
        //当前船员
        int CurrentCrew = boat.getCrew();
        int Num;
        //招募船员
        if (remainStamina >= 2){
            if (role.getID().equals("船长")){
                Num = random.nextInt(7)+2;
            }else {
                Num = random.nextInt(7);
            }
            //消耗体力
            remainStamina = remainStamina - 2;
            //当前船员增加
            CurrentCrew = CurrentCrew + Num;
            //同步数据
            role.setStamina(remainStamina);
            boat.setCrew(CurrentCrew);
            System.out.println("您招募到了"+Num+"位船员");
            System.out.println("剩余体力 "+role.getStamina()+"点");
        } else {
            System.out.println("体力不够，请选择其他行动");
        }

    }
    public void DiscoverIsland(Role role,Resources resources){
        //剩余资源
        int remainWood = resources.getWood();
        int remainWater = resources.getWater();
        int remainFood = resources.getFood();
        int remainMedical = resources.getMedical();
        int remainStamina = role.getStamina();
        //获得的资源
        int GetWood;
        int GetWater;
        int GetFood;
        int GetMedical;
        if(remainStamina >= 3){
            remainStamina = remainStamina -3;
            if (role.getID().equals("水手")){
                //水手获得的资源
                GetWood = random.nextInt(6)+2;
                GetWater = random.nextInt(3)+2;
                GetFood = random.nextInt(3)+2;
                GetMedical = random.nextInt(3);
            } else {
                //其他身份获得的资源
                GetWood = random.nextInt(6);
                GetWater = random.nextInt(3);
                GetFood = random.nextInt(3);
                GetMedical = random.nextInt(2);
            }
            remainWood = remainWood + GetWood;
            remainWater = remainWater + GetWater;
            remainFood = remainFood + GetFood;
            remainMedical = remainMedical + GetMedical;
            //将变化的数据重新同步
            resources.setWood(remainWood);
            resources.setFood(remainFood);
            resources.setWater(remainWater);
            resources.setMedical(remainMedical);
            role.setStamina(remainStamina);
            System.out.println("您探索岛屿完毕,消耗了3点体力,获得了"+ GetWood + "木头," + GetFood + "份食物," + GetWater + "份水," + GetMedical + "份医疗资源");
            System.out.println("剩余体力 "+role.getStamina()+"点");
        } else {
            System.out.println("体力不够，请选择其他行动");
        }
    }
    //治疗自身
    public void TreatYourSelf(Role role,Resources resources){
        //剩余血量
        int remainBlood = role.getHealth();
        //剩余医疗资源
        int remainMedical = resources.getMedical();
        //剩余体力
        int remainStamina = role.getStamina();
        //判断剩余医疗资源和体力是否足够
        if(remainMedical>0&&remainStamina>=3){
            //消耗1个医疗资源和3点体力恢复20滴血
            remainBlood= remainBlood+20;
            remainMedical = remainMedical-1;
            remainStamina = remainStamina - 3;
            role.setHealth(remainBlood,role);
            role.setStamina(remainStamina);
            resources.setMedical(remainMedical);
            System.out.println("您治疗了自身,消耗了1份医疗资源,恢复了20点生命");
            System.out.println("剩余体力 "+role.getStamina()+"点");
            //判断是体力不够还是医疗资源不够
        }else if (remainStamina<3){
            System.out.println("体力不够，无法治疗");
        }else {
            System.out.println("医疗资源不够，无法治疗");
        }
    }
    //修理船只
    public void RepairShips(Role role,Resources resources,Boat boat){
        //剩余耐久
        int remainDurable = boat.getDurable();
        //剩余木头资源
        int remainWood = resources.getWood();
        //剩余体力
        int remainStamina = role.getStamina();
        //判断职业是否是维修工
        if (role.getID().equals("维修工")){
            //判断体力和木头资源是否足够
            if (remainWood>=2&&remainStamina>=3){
                //消耗体力和木头资源进行维修
                remainWood = remainWood -2;
                remainStamina = remainStamina -3;
                remainDurable = remainDurable +20;
                role.setStamina(remainStamina);
                boat.setDurable(remainDurable);
                resources.setWood(remainWood);
                System.out.println("您维修了血色玛丽亚号,消耗了2根木头和2点体力,恢复了20点耐久");
                System.out.println("剩余体力 "+role.getStamina()+"点");
                //判断无法维修的原因
            } else if (remainStamina <3){
                System.out.println("体力不够，无法进行维修");
            } else {
                System.out.println("木头资源不够,需要两根木头,无法进行维修");
            }
        } else {
            //判断体力和木头资源是否足够
            if (remainWood>=3&&remainStamina>=3){
                //消耗体力和木头资源进行维修
                remainWood = remainWood -3;
                remainStamina = remainStamina -3;
                remainDurable = remainDurable +20;
                role.setStamina(remainStamina);
                boat.setDurable(remainDurable);
                resources.setWood(remainWood);
                System.out.println("您维修了血色玛丽亚号,消耗了3根木头和3点体力,恢复了20点耐久");
                System.out.println("剩余体力 "+role.getStamina()+"点");
                //判断无法维修的原因
            } else if (remainStamina <3){
                System.out.println("体力不够，无法进行维修");
            } else {
                System.out.println("木头资源不够,需要3根木头,无法进行维修");
            }

        }
    }

    //随机抽取角色职业
    public void SelRole(Role role){
        int tempi;
        String temps;
        //职业类别
        String[] id = {"水手","船长","厨师","维修工","领航员"};
        //职业血量
        int[] Health = {100,80,120,70,90};
        //职业体力
        int[] stamina = {6,7,7,8,6};
        //职业能力
        String[] able = {"资源搜索家","威名远扬","强壮身体","修理精通","前进四"};
        //随机抽取
        Random random = new Random();
        int index = random.nextInt(5);
        //设置职业
        temps=id[index];
        role.setID(temps);
        //设置初始血量
        tempi=Health[index];
        role.setHealthPlus(tempi);
        role.setHealth(tempi,role);
        //设置初始生病状态
        role.setBuff(1);
        //设置初始体力和体力上限
        tempi=stamina[index];
        role.setStaminaplus(tempi);
        role.setStamina(tempi);
        //设置特殊能力
        temps=able[index];
        role.setAbility(temps);

    }
    public int getStaminaplus() {
        return staminaplus;
    }
    public void setStaminaplus(int staminaplus) {
        this.staminaplus = staminaplus;
    }
    //用于每回合重置体力
    public void ResetStamina(Role role){
        int temp = (int)(role.getStaminaplus()*role.getBuff());
        role.setStamina(temp);
    }

    public Role() {
    }



    /**
     * 获取
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * 设置
     * @param health
     */
    public void setHealth(int health,Role role) {

        //验证各职业血量上限
        if (role.getID().equals("水手")){
            if (health<=100&&health>=0){
                this.health = health;
            }else if (health>100){
                this.health = 100;
            }else {
                this.health = 0;
            }
        }
        if (role.getID().equals("船长")){
            if (health<=80&&health>=0){
                this.health = health;
            }else if (health>80){
                this.health = 80;
            }else {
                this.health = 0;
            }
        }
        if (role.getID().equals("厨师")){
            if (health<=120&&health>=0){
                this.health = health;
            }else if (health>120){
                this.health = 120;
            }else {
                this.health = 0;
            }
        }
        if (role.getID().equals("维修工")){
            if (health<=70&&health>=0){
                this.health = health;
            }else if (health>70){
                this.health = 70;
            }else {
                this.health = 0;
            }
        }
        if (role.getID().equals("领航员")){
            if (health<=90&&health>=0){
                this.health = health;
            }else if (health>90){
                this.health = 90;
            }else {
                this.health = 0;
            }
        }
    }

    /**
     * 获取
     * @return stamina
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * 设置
     * @param stamina
     */
    public void setStamina(int stamina) {
            this.stamina = stamina;
    }

    /**
     * 获取
     * @return ability
     */
    public String getAbility() {
        return ability;
    }

    /**
     * 设置
     * @param ability
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }

    /**
     * 获取
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /**
     * 设置
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * 获取
     * @return buff
     */
    public double getBuff() {
        return buff;
    }

    /**
     * 设置
     * @param buff
     */
    public void setBuff(double buff) {
        this.buff = buff;
    }

    /**
     * 获取
     * @return healthPlus
     */
    public int getHealthPlus() {
        return healthPlus;
    }

    /**
     * 设置
     * @param healthPlus
     */
    public void setHealthPlus(int healthPlus) {
        this.healthPlus = healthPlus;
    }



    public String toString() {
        return "Role{ID = " + ID + ", health = " + health + ", healthPlus = " + healthPlus + ", staminaplus = " + staminaplus + ", stamina = " + stamina + ", ability = " + ability + ", buff = " + buff + ", random = " + random + "}";
    }
}
