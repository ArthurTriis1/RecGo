package br.edu.ufpe.recife.tads.recgo.models.dto;

import java.util.Objects;

public class LevelInfo {
    private static final Integer LEVEL_THRESHOLD = 100;

    private Integer level;
    private Integer experience;
    private Integer partialExperience;

    public LevelInfo(User user) {
        if(user.getStorageItems() == null || user.getStorageItems().size() <= 0){
            this.level = 0;
            this.experience = 0;
            this.partialExperience = 0;
            return;
        }

        this.experience = user.getStorageItems().stream().map(Item::getExperience).filter(Objects::nonNull).reduce(0, Integer::sum);

        this.level = this.experience / LEVEL_THRESHOLD;

        this.partialExperience = this.experience % LEVEL_THRESHOLD;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getPartialExperience() {
        return partialExperience;
    }
}
