package appline;

import lombok.Data;

@Data
public class GenerateUsers {
    private Integer amountUsers;
    private String nameKey;
    private Integer amountVacantSim;

    public Integer getAmountUsers() {
        return amountUsers;
    }

    public void setAmountUsers(Integer amount) {
        this.amountUsers = amount;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public Integer getAmountVacantSim() {
        return amountVacantSim;
    }

    public void setAmountVacantSim(Integer amountVacantSim) {
        this.amountVacantSim = amountVacantSim;
    }
}
