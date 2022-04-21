package com.mobile.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;

    private final static int IS_PLUS = 2;
    private final static int IS_MINUS = 3;
    private final static int IS_MULTIPLY = 4;
    private final static int IS_DIVIDE = 5;

    private Button mBTNZero, mBTNOne, mBTNTwo, mBTNThree, mBTNFour, mBTNFive, mBTNSix, mBTNSeven, mBTNEight, mBTNNine, mBTNEqual, mBTNPlus, mBTNMinus, mBTNMul, mBTNDiv, mBTNClear;
    private TextView mTVInputNumbers;

    private double firstNumber = 0, secondNumber = 0;
    private int USER_OPERAND_CHOICE = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding views with ids of xml layout
        findViewsById();

        // adding click listener to perform operations
        mBTNZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("0");
            }
        });

        mBTNOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("1");
            }
        });

        mBTNTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("2");
            }
        });

        mBTNThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("3");
            }
        });

        mBTNFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("4");
            }
        });

        mBTNFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("5");
            }
        });

        mBTNSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("6");
            }
        });

        mBTNSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("7");
            }
        });

        mBTNEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("8");
            }
        });

        mBTNNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber("9");
            }
        });

        mBTNDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperand("/", IS_DIVIDE);
            }
        });

        mBTNMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperand("*", IS_MULTIPLY);
            }
        });

        mBTNMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperand("-", IS_MINUS);
            }
        });

        mBTNPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperand("+", IS_PLUS);
            }
        });

        mBTNEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        mBTNClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });

    }

    private void clearAll() {
        mTVInputNumbers.setText("");
        USER_OPERAND_CHOICE = 9;
        firstNumber = 0;
        secondNumber = 0;
    }

    private void calculate() {
        String outputDisplay = mTVInputNumbers.getText().toString().trim();
        if(!outputDisplay.isEmpty() && defineLastCharacter(outputDisplay) != IS_OPERAND) {
            String second = "";
            if(USER_OPERAND_CHOICE == IS_PLUS) {
                second = outputDisplay.substring(outputDisplay.indexOf("+") + 1);
            }else if(USER_OPERAND_CHOICE == IS_MINUS) {
                second = outputDisplay.substring(outputDisplay.indexOf("-") + 1);
            }else if(USER_OPERAND_CHOICE == IS_DIVIDE) {
                second = outputDisplay.substring(outputDisplay.indexOf("/") + 1);
            }else if(USER_OPERAND_CHOICE == IS_MULTIPLY) {
                second = outputDisplay.substring(outputDisplay.indexOf("*") + 1);
            }
            secondNumber = Integer.parseInt(second);
            if(firstNumber != 0 && secondNumber != 0 && USER_OPERAND_CHOICE != 9) {
                Log.i("TESTING", firstNumber + " first");
                Log.i("TESTING", secondNumber + " second");

                double result = 0;
                if(USER_OPERAND_CHOICE == IS_PLUS) {
                    result = firstNumber+secondNumber;
                }else if(USER_OPERAND_CHOICE == IS_MINUS) {
                    result = firstNumber-secondNumber;
                }else if(USER_OPERAND_CHOICE == IS_DIVIDE) {
                    result = firstNumber/secondNumber;
                }else if(USER_OPERAND_CHOICE == IS_MULTIPLY) {
                    result = firstNumber*secondNumber;
                }
                clearAll();
                mTVInputNumbers.setText(result + "");
            }
        }
    }

    private void addOperand(String operand, int operandCode) {
        String outputDisplayValue = mTVInputNumbers.getText().toString().trim();

        if(!outputDisplayValue.isEmpty()) {
            if(USER_OPERAND_CHOICE == 9) {
                if(firstNumber == 0) {
                    firstNumber = Integer.parseInt(outputDisplayValue);
                }else {
                    secondNumber = Integer.parseInt(outputDisplayValue);
                }
                mTVInputNumbers.setText(outputDisplayValue + operand);
                USER_OPERAND_CHOICE = operandCode;
            }
        }
    }

    private void addNumber(String number)
    {
        int operationLength = mTVInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = mTVInputNumbers.getText().charAt(operationLength - 1) + "";
            Log.i(MainActivity.class.toString(), lastCharacter);
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0")) {
                mTVInputNumbers.setText(number);
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND)
            {
                mTVInputNumbers.setText(mTVInputNumbers.getText() + number);
            }
        } else
        {
            mTVInputNumbers.setText(mTVInputNumbers.getText() + number);
        }
    }

    private int defineLastCharacter(String lastCharacter)
    {
        try {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e) {
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("*") || lastCharacter.equals("/")))
            return IS_OPERAND;
        return -1;
    }

    private void findViewsById() {
        mBTNZero = findViewById(R.id.button_zero);
        mBTNOne = findViewById(R.id.button_one);
        mBTNTwo = findViewById(R.id.button_two);
        mBTNThree = findViewById(R.id.button_three);
        mBTNFour = findViewById(R.id.button_four);
        mBTNFive = findViewById(R.id.button_five);
        mBTNSix = findViewById(R.id.button_six);
        mBTNSeven = findViewById(R.id.button_seven);
        mBTNEight = findViewById(R.id.button_eight);
        mBTNNine = findViewById(R.id.button_nine);

        mBTNEqual = findViewById(R.id.button_equal);
        mBTNPlus = findViewById(R.id.button_addition);
        mBTNMinus = findViewById(R.id.button_subtraction);
        mBTNMul = findViewById(R.id.button_multiplication);
        mBTNDiv = findViewById(R.id.button_division);
        mBTNClear = findViewById(R.id.button_clear);

        mTVInputNumbers = findViewById(R.id.textView_input_numbers);
    }

}