public class example {
    public static void main(String[] args) {
        
    }
}

// Command Interface
interface Command {
    void execute();
}

// Receiver Class
class Light {
    void turnOn() {
        System.out.println("The light is on.");
    }

    void turnOff() {
        System.out.println("The light is off.");
    }
}

// Concrete Command Classes
class LightOnCommand implements Command {
    private final Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private final Light light;

    LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker Class
class RemoteControl {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void pressButton() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set.");
        }
    }
}
