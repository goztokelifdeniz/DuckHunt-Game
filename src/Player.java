public class Player {
    int ammo_left;
    int ducks_killed;
    double scale_choice;
    double volume_choice;
    int level;

    Player(){
        this.scale_choice = 3;
        this.volume_choice = 0.025;
        this.ducks_killed = 0;
        this.level = 1;
        this.ammo_left = this.level * 3;
    }

    public void reset_ammo(){
        this.ammo_left = this.level * 3;
    }
}
