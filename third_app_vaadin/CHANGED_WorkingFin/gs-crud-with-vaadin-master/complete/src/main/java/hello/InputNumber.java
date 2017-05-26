package hello;

/**
 * Created by Vladkuz on 25.05.2017.
 */
public class InputNumber {

    private String input;

    private String output;

    protected InputNumber(){
    }

    public InputNumber(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "InputNumber{" +
                "input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
