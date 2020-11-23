package models;

public class DistributorView {
    private String username;
    private float fee;
    private int distributorId;
    private int rating;

    public DistributorView(int distributorId, String username, float fee, int rating) {
        this.distributorId = distributorId;
        this.username = username;
        this.fee = fee;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    @Override
    public String toString() {
        return "DistributorRow{" +
                "username='" + username + '\'' +
                ", fee=" + fee +
                ", distributorId=" + distributorId +
                ", rating=" + rating +
                '}';
    }
}
