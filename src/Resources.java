public class Resources {
    private int wood;
    private int water;
    private int food;
    private int medical;

    public Resources() {
    }

    public Resources(int wood, int water, int food, int medical) {
        this.wood = wood;
        this.water = water;
        this.food = food;
        this.medical = medical;
    }

    /**
     * 获取
     * @return wood
     */
    public int getWood() {
        return wood;
    }

    /**
     * 设置
     * @param wood
     */
    public void setWood(int wood) {
        if(wood<0){
            this.wood = 0;
        } else {
            this.wood = wood;
        }
    }

    /**
     * 获取
     * @return water
     */
    public int getWater() {
        return water;
    }

    /**
     * 设置
     * @param water
     */
    public void setWater(int water) {
        if(water<0){
            this.water = 0;
        } else {
            this.water = water;
        }
    }

    /**
     * 获取
     * @return food
     */
    public int getFood() {
        return food;
    }

    /**
     * 设置
     * @param food
     */
    public void setFood(int food) {
        if (food<0){
            this.food = 0;
        }else {
            this.food = food;
        }
    }

    /**
     * 获取
     * @return medical
     */
    public int getMedical() {
        return medical;
    }

    /**
     * 设置
     * @param medical
     */
    public void setMedical(int medical) {
        if (medical <0){
            this.medical = 0;
        } else {
            this.medical = medical;
        }
    }

    public String toString() {
        return "Resources{wood = " + wood + ", water = " + water + ", food = " + food + ", medical = " + medical + "}";
    }
}
