import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("欢迎游玩血色玛丽亚号！");
        //随机抽取职业
        Role role = new Role();
        role.SelRole(role);
        //开场对白
        System.out.println("您的职业是 "+role.getID());
        System.out.println("您的职业能力为: "+role.getAbility());
        System.out.println("在你昏迷之前,血色玛丽号经历了一场掠夺,你躲在了一块用来隐匿的木板下面躲过了一劫");
        System.out.println("现在那群海盗已经离开了,船上除了你空无一人,血色玛丽亚号也变得残破不堪");
        System.out.println("万幸的是,你运送的至宝  玛丽亚之眼  没有丢失");
        System.out.println("你将继续踏上旅程,把玛丽亚之眼运送到大洋的彼岸");
        Boat boat = new Boat(100,0,0);
        Resources resources = new Resources(0,1,1,0);
        RandomActive randomActive = new RandomActive();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入任意键继续游戏");
        String active = scanner.next();
        //用于判断是否是非生病状态的体力下降
        //用于计算回合数
        int count = 0;
        //游戏开始,退出循环则游戏结束
        outerLoop: while(true){
            //回合+1
            count++;
            System.out.println("当前回合数: "+count+" 您的职业是: "+role.getID());
            //每回合开始重置体力
            role.ResetStamina(role);
            //输出角色信息
            role.ViewSelf(role,resources,boat);

            //消耗体力做出行动
            outerLoop2:while (role.getStamina()>0){
                System.out.println("输入1~7做出行动");
                System.out.println("1.治疗自身(3)  2.修理血色玛丽亚号(3)  3.探索岛屿(3)  4.招募船员(2)  5.加速前进(1) 6.查看自身信息(0) 7.结束本回合");
                active = scanner.next();
                //判断做出的行动
                switch (active){
                    //治疗自身
                    case "1":
                        role.TreatYourSelf(role,resources);
                        break;
                    //修理船只
                    case "2":
                        role.RepairShips(role,resources,boat);
                        break;
                    case "3":
                    //探索岛屿
                        role.DiscoverIsland(role,resources);
                        break;
                    case "4":
                    //招募船员
                        role.GetCrew(role,boat);
                        break;
                    case "5":
                    //加速前进
                        role.Moving(role,resources,boat);
                        if (boat.getMiles() == 150){
                            break outerLoop;
                        }
                        break;
                    case "6":
                    //查看自身
                        role.ViewSelf(role,resources,boat);
                        break;
                    case "7":
                    //结束回合
                        System.out.println("主动结束回合，进入下一个回合");
                        break outerLoop2;
                }
                if (role.getStamina() == 0){
                    System.out.println("您已经没有多余的体力做出行动,将自动进入下一回合");
                    System.out.println("请输入任意键继续...");
                    active = scanner.next();
                }
            }
            //每回合消耗食物和水,若资源不足则体力下降
            if (resources.getFood()==0 && role.getBuff() == 1){
                if (role.getID().equals("厨师")){
                    role.setBuff(0.7);
                    System.out.println("您因为饥饿体力下降,但因为身体强壮,还能保持70%的体力");
                }else {
                    role.setBuff(0.5);
                    System.out.println("您因为饥饿,只有平时50%的体力");
                }
            } else if (resources.getWater()==0 && role.getBuff() == 1){
                if (role.getID().equals("厨师")){
                    role.setBuff(0.7);
                    System.out.println("您因为口渴体力下降,但因为身体强壮,还能保持70%的体力");
                }else {
                    role.setBuff(0.5);
                    System.out.println("您因为口渴,只有平时50%的体力");
                }
            } else{
                //消耗食物和水分
                resources.setFood(resources.getFood()-1);
                resources.setWater(resources.getWater()-1);
                System.out.println("您消耗了一份食物和一份水来维持体力");
                //若原先处于非生病状态的体力下降状态则恢复体力
                if (role.getBuff()!=1){
                    role.setBuff(1);
                    System.out.println("您补充了食物和水分,体力恢复了");
                }
            }

            System.out.println("回合结束,随机事件判定中...");
            randomActive.RandomEvent(role,boat,resources);
            //判断游戏失败条件是否达成
            if (boat.getDurable() == 0||role.getHealth()==0){
                break;
            }
            System.out.println("随机事件结束");
            System.out.println("请输入任意键继续...");
            active = scanner.next();
        }
        if (boat.getMiles()==150){
            System.out.println("您成功到达目的地 大洋彼岸 将玛丽亚之眼送达,游戏胜利！！！");
        }else if (boat.getDurable() == 0){
            System.out.println("血色玛丽亚号沉没了,玛丽亚之眼与您一起葬身在了茫茫海洋之中,游戏失败...");
        }else if (role.getHealth()==0){
            System.out.println("您在血色玛丽亚号上战死,玛丽亚之眼落入了海盗之手,游戏失败...");
        }
    }
}
