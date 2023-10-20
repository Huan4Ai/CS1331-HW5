public class BlueAstronaut extends Player implements Crewmate{
    private int numTasks;
    private int taskSpeed;

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }

    public BlueAstronaut(String name) {
        this(name, 15, 6, 10);
    }

    @Override
    public void emergencyMeeting() {
        if (!isFrozen()) {
            Player mostSus = null;
            int maxSusLevel = -1;

            for (Player player : getPlayers()) {
                if (!player.isFrozen() && player.getSusLevel() > maxSusLevel) {
                    maxSusLevel = player.getSusLevel();
                    mostSus = player;
                }
            }

            if (mostSus != null) {
                mostSus.setFrozen(true);
                System.out.println(mostSus.getName() + " was voted off!");
                gameOver();
            }
        }
    }

    @Override
    public void completeTask() {
        if (!isFrozen()) {
            if (taskSpeed > 20) {
                numTasks -= 2;
            } else {
                numTasks--;
            }
            numTasks = Math.max(0, numTasks);
            if (numTasks == 0) {
                System.out.println("I have completed all my tasks");
                int susReduction = getSusLevel() / 2;
                setSusLevel(getSusLevel() - susReduction);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BlueAstronaut that = (BlueAstronaut) o;
        return numTasks == that.numTasks && taskSpeed == that.taskSpeed;
    }

    @Override
    public String toString() {
        String frozenString = isFrozen() ? "frozen" : "not frozen";
        String output = "My name is " + this.getName() + ", and I have a susLevel of " + this.getSusLevel() + ". I am currently " + frozenString + ". I have " + numTasks + " left over";
        if (this.getSusLevel() > 15) {
            return output.toUpperCase();
        } else {
            return output;
        }
    }

//    @Override
//    public String toString() {
//        String playerInfo = super.toString();
//        String frozenString = isFrozen() ? "frozen" : "not frozen";
//        String skillString = getSusLevel() > 15 ? skill.toUpperCase() : skill;
//
//        return playerInfo.replace("not frozen", frozenString).replace("player!", skillString + " player!");
//    }

    public int getNumTasks() {
        return numTasks;
    }

    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }

    public int getTaskSpeed() {
        return taskSpeed;
    }

    public void setTaskSpeed(int taskSpeed) {
        this.taskSpeed = taskSpeed;
    }


}
