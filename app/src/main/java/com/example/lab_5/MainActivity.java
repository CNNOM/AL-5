package com.example.lab_5;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GesturePoint;
import android.gesture.GestureStroke;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private GestureLibrary gestureLibrary;
    private TextView resultTextView;
    private TextView logTextView; // Добавляем TextView для логов
    private StringBuilder inputNumber;
    private int targetNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        logTextView = findViewById(R.id.logTextView); // Находим TextView для логов
        inputNumber = new StringBuilder();
        targetNumber = (int) (Math.random() * 100); // Загадываем случайное число от 0 до 99

        gestureLibrary = GestureLibraries.fromFile(getExternalFilesDir(null) + "/gestures");
        if (!gestureLibrary.load()) {
            log("Failed to load gestures"); // Логируем ошибку загрузки жестов
            finish();
        } else {
            log("Gestures loaded successfully"); // Логируем успешную загрузку жестов
        }

        // Создаем жесты программно
        createGestures();

        GestureOverlayView gestureOverlayView = findViewById(R.id.gestureOverlay);
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
                if (!predictions.isEmpty()) {
                    logTextView.setText("");
                    Prediction prediction = predictions.get(0);
                    if (prediction.score > 2.0) {
                        String name = prediction.name;
                        log("Число распознаноe: " + name); // Логируем распознанный жест
                        if (name.equals("V")) {
                            checkGuess();
                        } else {
                            inputNumber.append(name);
                            resultTextView.setText(inputNumber.toString());
                        }
                    } else {
                        log("Число не распознано");
//                        log("Gesture not recognized with score: " + prediction.score); // Логируем, если жест не распознан
                    }
                } else {
                    log("No predictions found"); // Логируем, если нет предсказаний
                }
            }

        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputNumber.length() > 0) {
                    inputNumber.deleteCharAt(inputNumber.length() - 1);
                    resultTextView.setText(inputNumber.toString());
                    log("Deleted last digit"); // Логируем удаление последней цифры
                } else {
                    log("No digits to delete"); // Логируем, если нет цифр для удаления
                }
            }
        });
    }



    private void createGestures() {
        gestureLibrary.addGesture("0", createGesture("0"));
        gestureLibrary.addGesture("1", createGesture("1"));
        gestureLibrary.addGesture("2", createGesture("2"));
        gestureLibrary.addGesture("3", createGesture("3"));
        gestureLibrary.addGesture("4", createGesture("4"));
        gestureLibrary.addGesture("5", createGesture("5"));
        gestureLibrary.addGesture("6", createGesture("6"));
        gestureLibrary.addGesture("7", createGesture("7"));
        gestureLibrary.addGesture("8", createGesture("8"));
        gestureLibrary.addGesture("9", createGesture("9"));
        gestureLibrary.addGesture("V", createGesture("V"));
        gestureLibrary.save();
    }

    private Gesture createGesture(String name) {
        Gesture gesture = new Gesture();
        ArrayList<GesturePoint> points = new ArrayList<>();

        switch (name) {
            case "0":
                points.add(new GesturePoint(100, 100, 0));
                points.add(new GesturePoint(150, 100, 100));
                points.add(new GesturePoint(150, 150, 200));
                points.add(new GesturePoint(100, 150, 300));
                points.add(new GesturePoint(100, 100, 400));
                break;
            case "1":
                points.add(new GesturePoint(100, 100, 0));
                points.add(new GesturePoint(100, 200, 100));
                break;
            case "2":
                points.add(new GesturePoint(100, 100, 0));
                points.add(new GesturePoint(150, 100, 100));
                points.add(new GesturePoint(150, 150, 200));
                points.add(new GesturePoint(100, 150, 300));
                points.add(new GesturePoint(100, 200, 400));
                points.add(new GesturePoint(150, 200, 500));
                break;
            case "3":
                points.add(new GesturePoint(100, 100, 0));
                points.add(new GesturePoint(150, 100, 100));
                points.add(new GesturePoint(150, 150, 200));
                points.add(new GesturePoint(100, 150, 300));
                points.add(new GesturePoint(150, 150, 400));
                points.add(new GesturePoint(150, 200, 500));
                points.add(new GesturePoint(100, 200, 600));
                break;
            case "4":
                points.add(new GesturePoint(100, 100, 0));
                points.add(new GesturePoint(100, 150, 100));
                points.add(new GesturePoint(150, 150, 200));
                points.add(new GesturePoint(150, 100, 300));
                points.add(new GesturePoint(150, 200, 400));
                break;
            case "5":
                points.add(new GesturePoint(150, 100, 0));
                points.add(new GesturePoint(100, 100, 100));
                points.add(new GesturePoint(100, 150, 200));
                points.add(new GesturePoint(150, 150, 300));
                points.add(new GesturePoint(150, 200, 400));
                points.add(new GesturePoint(100, 200, 500));
                break;
            case "6":
                points.add(new GesturePoint(150, 100, 0));
                points.add(new GesturePoint(100, 100, 100));
                points.add(new GesturePoint(100, 200, 200));
                points.add(new GesturePoint(150, 200, 300));
                points.add(new GesturePoint(150, 150, 400));
                points.add(new GesturePoint(100, 150, 500));
                break;
            case "7":
                points.add(new GesturePoint(100, 100, 0));
                points.add(new GesturePoint(150, 100, 100));
                points.add(new GesturePoint(150, 200, 200));
                break;
            case "8":
                points.add(new GesturePoint(150, 100, 0));  // Верхняя левая часть верхнего круга
                points.add(new GesturePoint(200, 150, 100));  // Верхняя правая часть верхнего круга
                points.add(new GesturePoint(150, 200, 200));  // Нижняя правая часть верхнего круга
                points.add(new GesturePoint(100, 150, 300));  // Нижняя левая часть верхнего круга
                points.add(new GesturePoint(150, 100, 400));  // Верхняя левая часть верхнего круга (замыкаем верхний круг)

                points.add(new GesturePoint(150, 200, 500));  // Верхняя левая часть нижнего круга
                points.add(new GesturePoint(200, 250, 600));  // Верхняя правая часть нижнего круга
                points.add(new GesturePoint(150, 300, 700));  // Нижняя правая часть нижнего круга
                points.add(new GesturePoint(100, 250, 800));  // Нижняя левая часть нижнего круга
                points.add(new GesturePoint(150, 200, 900));  // Верхняя левая часть нижнего круга (замыкаем нижний круг)
                break;
            case "9":
                points.add(new GesturePoint(150, 100, 0));  // Верхняя левая часть верхнего круга
                points.add(new GesturePoint(200, 150, 100));  // Верхняя правая часть верхнего круга
                points.add(new GesturePoint(150, 200, 200));  // Нижняя правая часть верхнего круга
                points.add(new GesturePoint(100, 150, 300));  // Нижняя левая часть верхнего круга
                points.add(new GesturePoint(150, 100, 400));  // Верхняя левая часть верхнего круга (замыкаем верхний круг)

                points.add(new GesturePoint(150, 200, 500));  // Верхняя левая часть нижнего круга
                points.add(new GesturePoint(200, 250, 600));  // Верхняя правая часть нижнего круга
                points.add(new GesturePoint(150, 300, 700));  // Нижняя правая часть нижнего круга
                points.add(new GesturePoint(100, 250, 800));  // Нижняя левая часть нижнего круга
                points.add(new GesturePoint(150, 200, 900));  // Верхняя левая часть нижнего круга (замыкаем нижний круг)

                points.add(new GesturePoint(150, 300, 1000));  // Нижняя точка нижнего круга
                points.add(new GesturePoint(150, 350, 1100));  // Нижняя точка нижнего круга (продолжение)
                break;
            case "V":
                points.add(new GesturePoint(100, 100, 0));  // Левая верхняя точка
                points.add(new GesturePoint(150, 200, 100));  // Нижняя точка
                points.add(new GesturePoint(200, 100, 200));  // Правая верхняя точка
                break;
        }

        GestureStroke stroke = new GestureStroke(points);
        gesture.addStroke(stroke);
        return gesture;
    }

    private void checkGuess() {
        if (inputNumber.length() > 0) {
            try {
                int guessedNumber = Integer.parseInt(inputNumber.toString());
                if (guessedNumber == targetNumber) {
                    resultTextView.setText("Поздравляем! Вы угадали число: " + targetNumber);
                    log("Correct guess: " + targetNumber); // Логируем правильное угадывание
                } else {
                    resultTextView.setText("Неверно! Загаданное число: " + targetNumber + ". Попробуйте еще раз.");
                    log("Incorrect guess: " + guessedNumber + ", target: " + targetNumber); // Логируем неправильное угадывание
                }
            } catch (NumberFormatException e) {
                log("Invalid number format: " + inputNumber.toString()); // Логируем ошибку формата числа
                resultTextView.setText("Неверный формат числа. Попробуйте еще раз.");
            } finally {
                inputNumber.setLength(0);
            }
        }
    }

    // Метод для добавления логов в logTextView
    private void log(String message) {
        if (logTextView != null) {
            logTextView.append(message + "\n");
        }
    }
}