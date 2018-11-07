package com.sajad.wich_on_larger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final int GAME_LEVEL_COUNT = 10;
    private final int LEFT_BUTTON = 0;
    private final int RIGHT_BUTTON = 1;
    private final int EQUAL_BUTTON = 0;

    protected Button left_button;
    protected Button right_button;
    protected Button equal_button;

    private TextView user_points;
    private TextView game_level;

    private int game_level_int = 0;
    private int points = 0;
    private int left_number_int;
    private int right_number_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find_view();
        initializevew();
        generateOnLevel();

    }

    private void initializevew() {
        user_points.setText("your point is : 0");
        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateAndCountinewGameLevel(LEFT_BUTTON);

            }
        });
        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateAndCountinewGameLevel(RIGHT_BUTTON);

            }
        });
        equal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateAndCountinewGameLevel(EQUAL_BUTTON);

            }
        });
    }

    private void evaluateAndCountinewGameLevel(int whatpressed) {
        evaluate(whatpressed);
        user_points.setText(getString(R.string.user_points, points));
        generateOnLevel();


    }

    private void evaluate(int what_pressed) {

        if (what_pressed == LEFT_BUTTON) {
            if (left_number_int > right_number_int) {
                points++;
            }
        } else if (what_pressed == RIGHT_BUTTON) {
            if (right_number_int > left_number_int) {
                points++;

            }

        } else if (what_pressed == EQUAL_BUTTON) {
            if (right_number_int == left_number_int) {
                points++;


            }

        }


    }

    private void generateOnLevel() {
        if (game_level_int == GAME_LEVEL_COUNT) {
            game_level.setText(getString(R.string.game_finish));
            return;
        }
        game_level_int++;
        game_level.setText(getString(R.string.game_level_string, game_level_int, GAME_LEVEL_COUNT));

        left_number_int = generate_number();
        right_number_int = generate_number();
        left_button.setText(String.valueOf(left_number_int));
        right_button.setText(String.valueOf(right_number_int));
    }

    private void find_view() {
        left_button = (Button) findViewById(R.id.left_button);
        right_button = (Button) findViewById(R.id.right_button);
        equal_button = (Button) findViewById(R.id.equal_button);
        user_points = (TextView) findViewById(R.id.user_points);
        game_level = (TextView) findViewById(R.id.game_level);
    }

    private int generate_number() {
        Random random = new Random();
        int number = random.nextInt();
        if (number < 0) {
            number = number * -1;
        }
        number = number % 30;
        return number;
    }
}
