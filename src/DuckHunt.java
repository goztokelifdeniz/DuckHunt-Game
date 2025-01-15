import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.List;


public class DuckHunt extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Player player = new Player();
        double scale = 3;
        double volume = 0.025;

        // Access the command line arguments
        Parameters params = getParameters();
        List<String> args = params.getRaw();

        if (args.size() == 2){
            scale = Double.parseDouble(args.get(0));
            volume = Double.parseDouble(args.get(1));
            player.scale_choice = scale;
            player.volume_choice = volume;
        }

        double WIDTH = player.scale_choice * 300;
        double HEIGHT = player.scale_choice * 200;

        final int[] chosen_background_image_index = {0};
        final int[] chosen_crosshair_image_index = {0};


        player.ammo_left = 3;

        Image favicon_image = new Image("assets/favicon/1.png");



        // TITLE SCREEN -----------------------------------------------------------------------------------------------
        StackPane title_screen_stackpane = new StackPane();
        Scene title_screen_scene = new Scene(title_screen_stackpane, WIDTH, HEIGHT);

        Image welcome_image = new Image("assets/welcome/1.png");
        ImageView welcome_imageview = new ImageView(welcome_image);
        welcome_imageview.setFitHeight(HEIGHT);
        welcome_imageview.setFitWidth(WIDTH);
        Text title_screen_texts = new Text("\n\n\n\n\nPRESS ENTER TO PLAY\nPRESS ESC TO EXIT");
        title_screen_texts.setTextAlignment(TextAlignment.CENTER);
        title_screen_texts.setFill(Color.ORANGE);
        title_screen_texts.setFont(new Font(10*player.scale_choice));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> title_screen_texts.setVisible(false)),
                new KeyFrame(Duration.seconds( 1), evt -> title_screen_texts.setVisible(true)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        String title_screen_music_path = "assets/effects/Title.mp3";
        Media title_screen_media = new Media(new File(title_screen_music_path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(title_screen_media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volume);
        title_screen_stackpane.getChildren().addAll(welcome_imageview, title_screen_texts);
        // ------------------------------------------------------------------------------------------------------------





        // BACKGROUND SELECTION SCREEN -----------------------------------------------------------------
        StackPane the_stackpane = new StackPane();
        Scene background_selection_scene = new Scene(the_stackpane, WIDTH, HEIGHT);
        Text background_selection_text = new Text("USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT");
        background_selection_text.setTextAlignment(TextAlignment.CENTER);
        title_screen_stackpane.setAlignment(background_selection_text, Pos.TOP_CENTER);
        background_selection_text.setFill(Color.ORANGE);
        background_selection_text.setFont(new Font(5*player.scale_choice));

        String intro_music_path = "assets/effects/Intro.mp3";
        Media intro_music_media = new Media(new File(intro_music_path).toURI().toString());
        MediaPlayer intro_music_mediaplayer = new MediaPlayer(intro_music_media);
        intro_music_mediaplayer.setCycleCount(1);

        File background_directory = new File("assets/background");
        File[] background_image_files_array = background_directory.listFiles();
        File foreground_directory = new File("assets/foreground");
        File[] foreground_image_files_array = foreground_directory.listFiles();
        File crosshair_directory = new File("assets/crosshair");
        File[] crosshair_image_files_array = crosshair_directory.listFiles();

        ImageView background_imageview = add_image_to_stackpane(background_image_files_array[0].getPath(),
                the_stackpane, true, HEIGHT, WIDTH);
        final ImageView[] crosshair_imageview = {add_image_to_stackpane(crosshair_image_files_array[0].getPath(),
                the_stackpane, false, HEIGHT, WIDTH)};
        // ------------------------------------------------------------------------------------------------------------


        Text level_text = new Text("Level " + player.level + "/6");
        level_text.setFill(Color.ORANGE);
        level_text.setFont(new Font(10*player.scale_choice));

        Text ammo_left_text = new Text("Ammo Left: " + player.ammo_left);
        ammo_left_text.setFill(Color.ORANGE);
        ammo_left_text.setFont(new Font(10*player.scale_choice));

        the_stackpane.setAlignment(level_text, Pos.TOP_CENTER);
        the_stackpane.setAlignment(ammo_left_text, Pos.TOP_RIGHT);
        the_stackpane.getChildren().addAll(level_text, ammo_left_text);




        //  GAME OVER --------------------------------------------------------------------------------------------------
        StackPane game_over_stackpane = new StackPane();
        Scene game_over_scene = new Scene(game_over_stackpane, WIDTH, HEIGHT);
        ImageView imageView_one = add_image_to_stackpane(background_image_files_array[0].getPath(), game_over_stackpane, true, HEIGHT, WIDTH);

        String game_over_music_string = "assets/effects/GameOver.mp3";
        Media game_over_media = new Media(new File(game_over_music_string).toURI().toString());
        MediaPlayer game_over_mediaplayer = new MediaPlayer(game_over_media);
        game_over_mediaplayer.setCycleCount(1);

        Text game_over_text = new Text("GAME OVER!");
        Text game_over_options_text = new Text("\n\n\nPress ENTER to play again\nPress ESC to exit");

        game_over_stackpane.setAlignment(game_over_text, Pos.CENTER);
        game_over_text.setFill(Color.ORANGE);
        game_over_text.setFont(new Font(10*player.scale_choice));

        game_over_stackpane.setAlignment(game_over_options_text, Pos.CENTER);
        game_over_options_text.setFill(Color.ORANGE);
        game_over_options_text.setFont(new Font(10*player.scale_choice));
        game_over_options_text.setTextAlignment(TextAlignment.CENTER);

        Timeline timeline_two = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> game_over_options_text.setVisible(false)),
                new KeyFrame(Duration.seconds( 1), evt -> game_over_options_text.setVisible(true)));
        timeline_two.setCycleCount(Animation.INDEFINITE);
        timeline_two.play();
        game_over_stackpane.getChildren().addAll(game_over_text, game_over_options_text);

        game_over_stackpane.setAlignment(level_text, Pos.TOP_CENTER);
        game_over_stackpane.setAlignment(ammo_left_text, Pos.TOP_RIGHT);
        game_over_stackpane.getChildren().addAll(level_text, ammo_left_text);
        // ------------------------------------------------------------------------------------------------------------





        // GAME COMPLETED ----------------------------------------------------------------------------------------------

        String game_completed_music_string = "assets/effects/GameCompleted.mp3";
        Media game_completed_media = new Media(new File(game_completed_music_string).toURI().toString());
        MediaPlayer game_completed_mediaplayer = new MediaPlayer(game_completed_media);
        game_completed_mediaplayer.setCycleCount(1);

        StackPane game_completed_stackpane = new StackPane();
        Scene game_completed_scene = new Scene(game_completed_stackpane, WIDTH, HEIGHT);

        ImageView imageView_two = add_image_to_stackpane(background_image_files_array[chosen_background_image_index[0]].getPath(),
                game_completed_stackpane, true, HEIGHT, WIDTH);

        Text game_completed_text = new Text("You have completed the game!");
        Text game_completed_options_text = new Text("\nPress ENTER to play again\nPress ESC to exit");

        game_completed_stackpane.setAlignment(game_completed_text, Pos.CENTER);
        game_completed_text.setFill(Color.ORANGE);
        game_completed_text.setFont(new Font(10*player.scale_choice));

        game_completed_stackpane.setAlignment(game_completed_options_text, Pos.CENTER);
        game_completed_options_text.setFill(Color.ORANGE);
        game_completed_options_text.setFont(new Font(10*player.scale_choice));
        game_completed_options_text.setTextAlignment(TextAlignment.CENTER);

        Timeline timeline_five = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> game_completed_options_text.setVisible(false)),
                new KeyFrame(Duration.seconds( 1), evt -> game_completed_options_text.setVisible(true)));
        timeline_two.setCycleCount(Animation.INDEFINITE);
        timeline_two.play();

        game_completed_stackpane.setAlignment(level_text, Pos.TOP_CENTER);
        game_completed_stackpane.setAlignment(ammo_left_text, Pos.TOP_RIGHT);
        game_completed_stackpane.getChildren().addAll(level_text, ammo_left_text);
        // ------------------------------------------------------------------------------------------------------------




        // LEVEL COMPLETED ----------------------------------------------------------------------------------------------

        StackPane level_completed_stackpane = new StackPane();
        Scene level_completed_scene = new Scene(level_completed_stackpane, WIDTH, HEIGHT);

        String level_completed_music_string = "assets/effects/LevelCompleted.mp3";
        Media level_completed_media = new Media(new File(level_completed_music_string).toURI().toString());
        MediaPlayer level_completed_mediaplayer = new MediaPlayer(level_completed_media);
        level_completed_mediaplayer.setCycleCount(1);

        ImageView imageView_three = add_image_to_stackpane(background_image_files_array[chosen_background_image_index[0]].getPath(),
                level_completed_stackpane, true, HEIGHT, WIDTH);
        Text level_completed_text = new Text("YOU WIN!");
        Text level_completed_options_text = new Text("\n\n\nPress ENTER to play next level");

        level_completed_stackpane.setAlignment(level_completed_text, Pos.CENTER);
        level_completed_text.setFill(Color.ORANGE);
        level_completed_text.setFont(new Font(10*player.scale_choice));
        level_completed_text.toFront();

        level_completed_stackpane.setAlignment(level_completed_options_text, Pos.CENTER);
        level_completed_options_text.setFill(Color.ORANGE);
        level_completed_options_text.setFont(new Font(10*player.scale_choice));
        level_completed_options_text.setTextAlignment(TextAlignment.CENTER);
        level_completed_options_text.toFront();

        Timeline timeline_six = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> level_completed_options_text.setVisible(false)),
                new KeyFrame(Duration.seconds( 1), evt -> level_completed_options_text.setVisible(true)));
        timeline_six.setCycleCount(Animation.INDEFINITE);
        timeline_six.play();

        level_completed_stackpane.getChildren().addAll(level_completed_text, level_completed_options_text);

        level_completed_stackpane.setAlignment(level_text, Pos.TOP_CENTER);
        level_completed_stackpane.setAlignment(ammo_left_text, Pos.TOP_RIGHT);
        level_completed_stackpane.getChildren().addAll(level_text, ammo_left_text);
        // ------------------------------------------------------------------------------------------------------------








        File foreground_image_file = find_the_suitable_foreground(foreground_image_files_array, background_image_files_array[chosen_background_image_index[0]]);
        ImageView foreground_imageView = add_image_to_stackpane(foreground_image_file.getPath(), the_stackpane, true, HEIGHT, WIDTH);


        Image bird_flying_to_upright_one = new Image("assets/duck_black/1.png");
        Image bird_flying_to_upright_two = new Image("assets/duck_black/2.png");
        Image bird_flying_to_upright_three = new Image("assets/duck_black/3.png");


        Image bird_flying_to_right_one = new Image("assets/duck_black/4.png");
        Image bird_flying_to_right_two = new Image("assets/duck_black/5.png");
        Image bird_flying_to_right_three = new Image("assets/duck_black/6.png");

        Image bird_dying = new Image("assets/duck_black/7.png");
        Image bird_falling =new Image("assets/duck_black/8.png");

        ImageView duck_imageview = new ImageView(bird_flying_to_right_one);
        duck_imageview.setPreserveRatio(true);
        duck_imageview.setFitWidth(duck_imageview.getImage().getWidth() *player.scale_choice/3);

        primaryStage.setTitle("HUBBM Duck Hunt");
        primaryStage.setScene(title_screen_scene);
        primaryStage.getIcons().add(favicon_image);
        primaryStage.show();

        Duck duck_one = new Duck();
        duck_one.imageView = duck_imageview;
        change_transition(player, duck_one.flying_translateTransition, WIDTH, HEIGHT);
        duck_one.flying_translateTransition.setCycleCount(Animation.INDEFINITE);
        duck_one.flying_translateTransition.setDuration(Duration.seconds(6));
        duck_one.flying_translateTransition.setNode(duck_one.imageView);
        duck_one.flying_translateTransition.setAutoReverse(true);

        ImageView bird_falling_imageview = new ImageView(bird_falling);

        String duck_falls_music_string = "assets/effects/LevelCompleted.mp3";
        Media duck_falls_media = new Media(new File(duck_falls_music_string).toURI().toString());
        MediaPlayer duck_falls_mediaplayer = new MediaPlayer(duck_falls_media);
        duck_falls_mediaplayer.setCycleCount(1);



        // keyboard interactions


        title_screen_scene.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            if (keyCode == KeyCode.ESCAPE){
                System.exit(0);
            } else if (keyCode == KeyCode.ENTER){
                primaryStage.setScene(background_selection_scene);
            }
        });

        background_selection_scene.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            if (keyCode.isArrowKey()){
                the_stackpane.getChildren().clear();
                if (keyCode == KeyCode.UP){
                    changing_the_setting(chosen_crosshair_image_index, crosshair_image_files_array, false);
                } else if (keyCode == KeyCode.DOWN){
                    changing_the_setting(chosen_crosshair_image_index, crosshair_image_files_array, true);
                } else if (keyCode == KeyCode.LEFT){
                    changing_the_setting(chosen_background_image_index, background_image_files_array, false);
                } else if (keyCode == KeyCode.RIGHT){
                    changing_the_setting(chosen_background_image_index, background_image_files_array, true);
                }

                crosshair_imageview[0] = set_background_and_crosshair(chosen_crosshair_image_index, crosshair_image_files_array,
                        chosen_background_image_index, background_image_files_array,
                        the_stackpane, HEIGHT, WIDTH);

            } else {
                if (keyCode == KeyCode.ESCAPE){
                    primaryStage.setScene(title_screen_scene);
                    primaryStage.show();
                    chosen_crosshair_image_index[0] = 0;
                    chosen_background_image_index[0] = 0;
                    Image cursorImage = new Image(crosshair_image_files_array[chosen_crosshair_image_index[0]].getPath());
                    ImageCursor imageCursor = new ImageCursor(cursorImage, 100, 100);
                    background_selection_scene.setCursor(imageCursor);
                    the_stackpane.getChildren().clear();
                    crosshair_imageview[0] = set_background_and_crosshair(chosen_crosshair_image_index, crosshair_image_files_array,
                            chosen_background_image_index, background_image_files_array,
                            the_stackpane, HEIGHT, WIDTH);

                    // turn back to title screen and reset background selection screen
                } else if (keyCode == KeyCode.ENTER){
                    mediaPlayer.stop();
                    intro_music_mediaplayer.setVolume(player.volume_choice);
                    intro_music_mediaplayer.setCycleCount(1);
                    intro_music_mediaplayer.play();

                    intro_music_mediaplayer.setOnEndOfMedia(() -> {
                        // level 1 ile oyuna başlama

                        foreground_imageView.toFront();
                        Image cursorImage = new Image(crosshair_image_files_array[chosen_crosshair_image_index[0]].getPath());
                        ImageCursor imageCursor = new ImageCursor(cursorImage);
                        the_stackpane.setCursor(imageCursor);

                        the_stackpane.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                            ImageCursor imageCursor2 = new ImageCursor(cursorImage);
                            the_stackpane.setCursor(imageCursor2);
                        });

                        the_stackpane.getChildren().remove(crosshair_imageview[0]);
                        the_stackpane.getChildren().add(duck_imageview);
                        the_stackpane.getChildren().addAll(level_text, ammo_left_text);
                        change_transition(player, duck_one.flying_translateTransition, WIDTH, HEIGHT);

                        final int[] scale_value = {-1};
                        duck_imageview.setScaleX(scale_value[0]);
                        Timeline timeline_four = new Timeline(new KeyFrame(Duration.seconds(6), evt -> {
                            scale_value[0] = -scale_value[0];
                            duck_imageview.setScaleX(scale_value[0]);
                        }));
                        timeline_four.setCycleCount(Animation.INDEFINITE);
                        timeline_four.play();

                        foreground_imageView.toFront();
                        duck_one.flying_translateTransition.play();

                        the_stackpane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                            String gunshot_string = "assets/effects/Gunshot.mp3";
                            Media gunshot_media = new Media(new File(gunshot_string).toURI().toString());
                            MediaPlayer gunshot_mediaplayer = new MediaPlayer(gunshot_media);
                            gunshot_mediaplayer.stop();
                            gunshot_mediaplayer.setCycleCount(1);
                            gunshot_mediaplayer.play();

                            double clicked_x_coordinate = event.getSceneX();
                            double clicked_y_coordinate = event.getSceneY();

                            duck_one.bounds = duck_one.imageView.getBoundsInParent();
                            Timeline bound_timeline = new Timeline(new KeyFrame(Duration.millis(1), evt -> {
                                duck_one.bounds = duck_one.imageView.getBoundsInParent();
                            }));
                            bound_timeline.setCycleCount(Animation.INDEFINITE);
                            bound_timeline.play();

                            if (duck_one.bounds.contains(clicked_x_coordinate, clicked_y_coordinate)){

                                if (gunshot_mediaplayer.getStatus() == MediaPlayer.Status.PLAYING){
                                    gunshot_mediaplayer.stop();
                                }

                                duck_falls_mediaplayer.play();

                                player.ducks_killed = player.ducks_killed + 1;
                                duck_one.flying_translateTransition.stop();
                                duck_one.imageView.setImage(bird_falling);
                                duck_one.falling_translateTransition.setNode(duck_one.imageView);
                                duck_one.falling_translateTransition.setByX(0);
                                duck_one.falling_translateTransition.setByY(HEIGHT);
                                duck_one.falling_translateTransition.setCycleCount(1);
                                duck_one.falling_translateTransition.setDuration(new Duration(5000));
                                foreground_imageView.toFront();
                                duck_one.falling_translateTransition.play();
                            }

                            player.ammo_left = player.ammo_left - 1;
                            ammo_left_text.setText("Ammo Left: " + player.ammo_left);

                            if (player.ammo_left == 0){
                                if (player.ducks_killed == 3){
                                    // this level is completed, so:
                                    if (player.level==6){
                                        // game completed
                                        primaryStage.setScene(game_completed_scene);
                                    } else {
                                        // go to next level
                                        duck_one.falling_translateTransition.setOnFinished(e5 -> {
                                            change_transition(player, duck_one.flying_translateTransition, WIDTH, HEIGHT);
                                            primaryStage.setScene(level_completed_scene);
                                            level_completed_mediaplayer.play();
                                        });

                                        background_selection_scene.setOnKeyPressed(e2 -> {
                                            KeyCode keyCode1 = e2.getCode();
                                            if (keyCode1 == KeyCode.ENTER){
                                                // PASS TO THE NEXT LEVEL
                                                player.level = player.level + 1;
                                                player.ducks_killed = 0;
                                                player.reset_ammo();
                                                level_text.setText("Level " + player.level + "/6");
                                                ammo_left_text.setText("Ammo Left: " + Integer.toString(player.ammo_left));
                                            }
                                        });

                                        primaryStage.setScene(level_completed_scene);

                                    }
                                } else {
                                    primaryStage.setScene(game_over_scene);
                                }
                            } else { // there's still ammo
                                duck_one.falling_translateTransition.setOnFinished(event1 -> {
                                    ImageView iv = new ImageView(bird_falling);
                                    iv.setPreserveRatio(true);
                                    iv.setFitWidth(iv.getImage().getWidth()*player.scale_choice/3);
                                    the_stackpane.getChildren().add(iv);
                                    duck_one.imageView = iv;
                                    change_transition(player, duck_one.flying_translateTransition, WIDTH, HEIGHT);
                                    duck_one.flying_translateTransition.setNode(iv);
                                    duck_one.flying_translateTransition.setCycleCount(Animation.INDEFINITE);
                                    duck_one.flying_translateTransition.setDuration(Duration.seconds(6));
                                    duck_one.flying_translateTransition.setAutoReverse(true);

                                    duck_one.flying_translateTransition.getNode().setVisible(true);
                                    duck_one.flying_translateTransition.play();
                                });


                            }
                        });
                    });
                }}
        });

        game_over_scene.setOnKeyPressed(e3 -> {
            if (game_over_mediaplayer.getStatus() == MediaPlayer.Status.PLAYING){
                game_over_mediaplayer.stop();
            }

            player.level = 1;
            player.ammo_left = 3;
            player.ducks_killed = 0;
            level_text.setText("Level " + player.level + "/6");
            ammo_left_text.setText("Ammo Left: " + player.ammo_left);

            KeyCode keyCode3 = e3.getCode();
            if (keyCode3 == KeyCode.ESCAPE){
                primaryStage.setScene(title_screen_scene);
            } else if (keyCode3 == KeyCode.ENTER){
                primaryStage.setScene(background_selection_scene);

            }
        });

        game_completed_scene.setOnKeyPressed(e4 -> {
            if (game_completed_mediaplayer.getStatus() == MediaPlayer.Status.PLAYING){
                game_completed_mediaplayer.stop();
            }

            KeyCode keyCode4 = e4.getCode();
            if (keyCode4 == KeyCode.ESCAPE){
                primaryStage.setScene(title_screen_scene);
            } else if (keyCode4 == KeyCode.ENTER){
                // start back from the first level
                player.level = 1;
                player.ammo_left = 3;
                player.ducks_killed = 0;
                level_text.setText("Level " + player.level + "/6");
                ammo_left_text.setText("Ammo Left: " + player.ammo_left);
                change_transition(player, duck_one.flying_translateTransition, WIDTH, HEIGHT);
                primaryStage.setScene(background_selection_scene);
            }
        });

        level_completed_scene.setOnKeyPressed(e3 -> {
            player.level = player.level + 1;
            player.ammo_left = 3;
            player.ducks_killed = 0;
            level_text.setText("Level " + player.level + "/6");
            ammo_left_text.setText("Ammo Left: " + player.ammo_left);

            KeyCode keyCode3 = e3.getCode();
            if (keyCode3 == KeyCode.ESCAPE){
                primaryStage.setScene(title_screen_scene);
            } else if (keyCode3 == KeyCode.ENTER){
                change_transition(player, duck_one.flying_translateTransition, WIDTH, HEIGHT);
                duck_one.flying_translateTransition.play();
                primaryStage.setScene(background_selection_scene);
            }
        });

    }

    public static TranslateTransition change_transition(Player player,TranslateTransition flying_translateTransition,
                                                 double WIDTH, double HEIGHT){

        if (flying_translateTransition.getStatus()== Animation.Status.RUNNING){
            flying_translateTransition.stop();
        }

        if(player.level == 1){
            flying_translateTransition.setFromX(WIDTH/2 - (5*player.scale_choice));
            flying_translateTransition.setFromY(0);
            flying_translateTransition.setToX(-(WIDTH/2) + (5*player.scale_choice));
            flying_translateTransition.setToY(0);
        } else if (player.level == 2){
            flying_translateTransition.setFromX(-(WIDTH/2) + (5*player.scale_choice));
            flying_translateTransition.setFromY(HEIGHT/2 - (5*player.scale_choice));
            flying_translateTransition.setToX(WIDTH/2 - (5*player.scale_choice));
            flying_translateTransition.setToY(-HEIGHT/2 + (5*player.scale_choice));
        } else if (player.level == 3) {
            flying_translateTransition.setFromX(WIDTH/2 - (5*player.scale_choice));
            flying_translateTransition.setFromY(HEIGHT/2 - (5*player.scale_choice));
            flying_translateTransition.setToX(-(WIDTH/2) + (5*player.scale_choice));
            flying_translateTransition.setToY(-HEIGHT/2 + (5*player.scale_choice));
        }
        flying_translateTransition.setDuration(new Duration(6000));
        return flying_translateTransition;
    }

    public static void find_the_background_image(int[] chosen_background_image_index, File[] background_files_array,
                                                 StackPane some_stackpane){
        for (File image_file : background_files_array){
            Image image = new Image(image_file.getPath());
            if (some_stackpane.getChildren().contains(image)){

            }
        }


    }
    public static ImageView add_image_to_stackpane(String image_path, StackPane stackPane, boolean fit_heigth_and_width, double HEIGHT, double WIDTH){
        Image image = new Image(image_path);
        ImageView imageview = new ImageView(image);
        if (fit_heigth_and_width){
            imageview.setFitHeight(HEIGHT);
            imageview.setFitWidth(WIDTH);
        } else {
            imageview.setScaleX(2);
            imageview.setScaleY(2);
        }
        stackPane.getChildren().add(imageview);
        return imageview;
    }
    public static void changing_the_setting(int[] to_change_image_index, File[] to_change_files_array,
                                            boolean are_we_changing_into_the_next_one){
        if (are_we_changing_into_the_next_one) {
            if (to_change_image_index[0] == (to_change_files_array.length - 1)){
                to_change_image_index[0] = 0;
            } else {
                to_change_image_index[0] = to_change_image_index[0] + 1;
            }
        } else {
            if (to_change_image_index[0] == 0) {
                to_change_image_index[0] = to_change_files_array.length - 1;
            } else {
                to_change_image_index[0] = to_change_image_index[0] - 1;
            }
        }
    }

    public static ImageView set_background_and_crosshair(int[] chosen_crosshair_image_index, File[] crosshair_files_array,
                                                    int[] chosen_background_image_index, File[] background_files_array,
                                                    StackPane background_selection_stackpane, double HEIGHT, double WIDTH){
        ImageView imageView = add_image_to_stackpane(background_files_array[chosen_background_image_index[0]].getPath(), background_selection_stackpane, true, HEIGHT, WIDTH);
        ImageView imageView_two = add_image_to_stackpane(crosshair_files_array[chosen_crosshair_image_index[0]].getPath(), background_selection_stackpane, false, HEIGHT, WIDTH);
        return imageView_two;
    }

    public static File find_the_suitable_foreground(File[] foreground_image_files_array, File background_file){
        for (File foreground_file : foreground_image_files_array){
            if (foreground_file.getName().equals(background_file.getName())){
                return foreground_file;
            }
        }
        return null;
    }
}