package com.cxw.leetcodecourse.petqueue;

public class PetWrapper {
    private Pet pet;
    private long time;

    public PetWrapper(Pet pet, long time) {
        this.pet = pet;
        this.time = time;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
