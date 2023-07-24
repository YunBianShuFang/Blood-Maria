import java.util.Random;

public class RandomActive {
    public RandomActive() {
    }

    //随机事件
    public void RandomEvent(Role role, Boat boat, Resources resources){
        //定义随机事件 储存于数组
        String[] Active = {"船员死亡","遭遇海盗","遇到商船","迷失方向","生病"};
        //定义index 作为数组的随机索引
        Random random = new Random();
        int index = random.nextInt(5);
        //记录随机事件前的各项数据
        //当前生病状态
        double CurrentBuff = role.getBuff();
        //剩余血量
        int remainBlood = role.getHealth();
        //剩余耐久
        int remainDurable= boat.getDurable();
        //剩余船员
        int remainCrew = boat.getCrew();
        //当前里程
        int currentmiles = boat.getMiles();
        //剩余资源
        int remainWood = resources.getWood();
        int remainWater = resources.getWater();
        int remainMedical = resources.getMedical();
        int remainFood = resources.getFood();
        //随机进行一个事件
        //事件1 船员死亡
        switch (Active[index]) {
            case "船员死亡":
                //死亡随机数量的船员(1~所有)
                if (remainCrew==0){
                    remainBlood = remainBlood-20;
                    role.setHealth(remainBlood,role);
                    System.out.println("血色玛丽亚号遭遇了海难,您受伤了,血量减少20");
                } else {
                    int num = random.nextInt(remainCrew) + 1;
                    remainCrew = remainCrew - num;
                    boat.setCrew(remainCrew);
                    System.out.println("血色玛丽亚号遭到了海难,死亡了"+num+"位船员");
                }

                //事件2 遭遇海盗
                break;
            case "遭遇海盗":
                //随机生产海盗的数量
                int buccaneer = random.nextInt(21) + 10;
                //若海盗胜利 损失随机资源和一半的船员
                if (buccaneer >= remainCrew) {
                    //战败丢失的水
                    int DropWater = 0;
                    if (remainWater > 0){
                        DropWater = random.nextInt(remainWater) + 1;
                    }
                    //剩余的水
                    remainWater = remainWater - DropWater;
                    //丢失的食物
                    int DropFood = 0;
                    if (remainFood>0){
                        DropFood = random.nextInt(remainFood) + 1;
                    }
                    //剩余的食物
                    remainFood = remainFood - DropFood;
                    //丢失的医疗物资
                    int DropMedical = 0;
                    if (remainMedical>0){
                        DropMedical = random.nextInt(remainMedical) + 1;
                    }
                    //剩余的医疗物资
                    remainMedical = remainMedical - DropMedical;
                    //丢失的木头
                    int DropWood = 0;
                    if (remainWood>0) {
                        DropWood = random.nextInt(remainWood) + 1;
                    }
                    //剩余的木头
                    remainWood = remainWood - DropWood;
                    //战死的船员
                    int DeadCrew = 0;
                    if (remainCrew >= 2) {
                        DeadCrew = remainCrew / 2;
                    }
                    //剩余的船员
                    remainCrew = remainCrew - DeadCrew;
                    //船只耐久减少
                    remainDurable = remainDurable - 30;
                    //角色血量减少
                    remainBlood = remainBlood - 30;
                    //判断当前里程数
                    //里程足够 -10 里程,不够则归零
                    if (currentmiles >= 5) {
                        //战败信息提示
                        System.out.println("您被海盗击败！落荒而逃！航海进度-5");
                        currentmiles = currentmiles - 5;
                    } else {
                        System.out.println("您被海盗击败！落荒而逃！航海进度-" + currentmiles);
                        currentmiles = 0;
                    }
                    //战败信息提示
                    System.out.println("您损失了" + DropWood + "木头," + DropFood + "份食物," + DropWater + "份水," + DropMedical + "份医疗资源," + DeadCrew + "位船员");
                    System.out.println("在您与海盗的战斗中 船只的耐久减少了30,血量减少了30");
                } else {
                    //获得的水
                    int GetWater = random.nextInt(3) + 1;
                    //剩余的水
                    remainWater = remainWater + GetWater;
                    //获得的食物
                    int GetFood = random.nextInt(3) + 1;
                    //剩余的食物
                    remainFood = remainFood + GetFood;
                    //获得的医疗物资
                    int GetMedical = random.nextInt(3);
                    //剩余的医疗物资
                    remainMedical = remainMedical + GetMedical;
                    //获得的木头
                    int GetWood = random.nextInt(10) + 1;
                    //剩余的木头
                    remainWood = remainWood + GetWood;
                    //战死的船员
                    int DeadCrew = random.nextInt(remainCrew / 2) + 1;
                    //俘虏海盗后的船员数量
                    remainCrew = remainCrew - DeadCrew + 5;
                    //剩余的船只耐久
                    remainDurable = remainDurable - 20;
                    //剩余的血量
                    remainBlood = remainBlood - 10;
                    //战斗胜利信息提示
                    System.out.println("您成功击败了海盗！");
                    System.out.println("您获得了" + GetWood + "木头," + GetFood + "份食物," + GetWater + "份水," + GetMedical + "份医疗资源,战死了" + DeadCrew + "位船员,俘获了5名海盗");
                    System.out.println("在您与海盗的战斗中 船只的耐久减少了20,血量减少了10");
                }
                //将变化的数据重新同步
                role.setHealth(remainBlood, role);
                boat.setDurable(remainDurable);
                boat.setCrew(remainCrew);
                boat.setMiles(currentmiles);
                resources.setWood(remainWood);
                resources.setMedical(remainMedical);
                resources.setFood(remainFood);
                resources.setWater(remainWater);
                //
                break;
            case "遇到商船":
                //获得的资源
                int GetWood = random.nextInt(6);
                int GetWater = random.nextInt(3);
                int GetFood = random.nextInt(3);
                int GetMedical = random.nextInt(2);
                remainWood = remainWood + GetWood;
                remainWater = remainWater + GetWater;
                remainFood = remainFood + GetFood;
                remainMedical = remainMedical + GetMedical;
                //事件结束提示信息
                System.out.println("您遇到了友好的商船");
                System.out.println("您获得了" + GetWood + "木头," + GetFood + "份食物," + GetWater + "份水," + GetMedical + "份医疗资源");
                if (CurrentBuff != 1) {
                    //治疗生病的状态
                    CurrentBuff = 1;
                    System.out.println("船长请来了医生将您的病治好了");
                }
                //将变化的数据重新同步
                resources.setWood(remainWood);
                resources.setMedical(remainMedical);
                resources.setFood(remainFood);
                resources.setWater(remainWater);
                role.setBuff(CurrentBuff);
                break;
            case "迷失方向":
                //随机迷失的距离
                int lost = random.nextInt(10)+1;
                //若小于迷失的距离则回到起点
                if (currentmiles>=lost){
                    currentmiles = currentmiles -lost;
                    System.out.println("一阵浓雾突然出现在海面上，你迷失了方向,航海进度减少了"+lost);
                }else {
                    currentmiles = 0;
                    System.out.println("一阵浓雾突然出现在海面上，你迷失了方向,回到了起点");
                }
                //同步里程数据
                boat.setMiles(currentmiles);
                break;
            case "生病":
                //判断是否是厨师
                if (role.getID().equals("厨师")){
                    role.setBuff(0.7);
                    System.out.println("您在船上生病了,但因为身体强壮,你还能保持70%的体力");
                }else {
                    role.setBuff(0.5);
                    System.out.println("您在船上生病了,你只有平时50%的体力");
                }
                break;
        }

    }
}
