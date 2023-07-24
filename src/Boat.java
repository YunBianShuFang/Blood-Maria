public class Boat {
    private int durable;
    private int crew;
    private int miles;

    public Boat() {
    }


    public Boat(int durable, int crew, int miles) {
        this.durable = durable;
        this.crew = crew;
        this.miles = miles;
    }

    /**
     * 获取
     * @return durable
     */
    public int getDurable() {
        return durable;
    }

    /**
     * 设置
     * @param durable 最高值为200，最低为0
     */
    public void setDurable(int durable) {
        if (durable>=0&&durable<=200){
            this.durable = durable;
        }else if(durable>200) {
            this.durable = 200;
        }else {
            this.durable = 0;
        }
    }

    /**
     * 获取
     * @return crew
     */
    public int getCrew() {
        return crew;
    }

    /**
     * 设置
     * @param crew 最高为30，最低为0
     */
    public void setCrew(int crew) {
        if (crew>=0&&crew<=30){
            this.crew = crew;
        }else if(crew>30) {
            this.crew = 30;
        }else {
            this.crew = 0;
        }
    }

    /**
     * 获取
     * @return miles
     */
    public int getMiles() {
        return miles;
    }

    /**
     * 设置
     * @param miles 最大值为100，最小值为0
     */
    public void setMiles(int miles) {
        if (miles>=0&&miles<=150){
            this.miles = miles;
        }else if(miles>150) {
            this.miles = 150;
        }else {
            this.miles = 0;
        }
    }

    public String toString() {
        return "Boat{durable = " + durable + ", crew = " + crew + ", miles = " + miles + "}";
    }
}
