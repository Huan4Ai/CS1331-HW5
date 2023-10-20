public class RedAstronaut extends Player implements Impostor {
    private String skill;

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill;
    }

    public RedAstronaut(String name) {
        this(name, 15, "experienced");
    }

    @Override
    public void emergencyMeeting() {
        if (!isFrozen()) {
            Player mostSuspicious = null;
            int maxSusLevel = -1;

            for (Player p : getPlayers()) {
                if (!p.isFrozen() && p != this) {
                    if (p.getSusLevel() > maxSusLevel) {
                        maxSusLevel = p.getSusLevel();
                        mostSuspicious = p;
                    } else if (p.getSusLevel() == maxSusLevel) {
                        mostSuspicious = null; // Two players with the same highest susLevel
                    }
                }
            }

            if (mostSuspicious != null) {
                mostSuspicious.setFrozen(true);
                System.out.println(mostSuspicious.getName() + " was voted off!");
            }

            gameOver();
        }
    }

    @Override
    public void freeze(Player p) {
        if (!isFrozen() && !p.isFrozen() && !(p instanceof Impostor)) {
            if (this.getSusLevel() < p.getSusLevel()) {
                p.setFrozen(true);
            } else {
                this.setSusLevel(this.getSusLevel() * 2);
            }
        }
        gameOver();
    }

    @Override
    public void sabotage(Player p) {
        if (!isFrozen() && !p.isFrozen() && !(p instanceof Impostor)) {
            if (getSusLevel() < 20) {
                p.setSusLevel(p.getSusLevel() + (int) (p.getSusLevel() * 0.5));
            } else {
                p.setSusLevel(p.getSusLevel() + (int) (p.getSusLevel() * 0.25));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RedAstronaut) {
            RedAstronaut player = (RedAstronaut) o;
            return this.getName().equals(player.getName()) && this.isFrozen() == player.isFrozen() && this.getSusLevel() == player.getSusLevel() && this.skill.equals(player.skill);
        }
        return false;
    }

    @Override
    public String toString() {
        String frozenString = isFrozen() ? "frozen" : "not frozen";
        String output = "My name is " + this.getName() + ", and I have a susLevel of " + this.getSusLevel() + ". I am currently " + frozenString + ". I am an " + skill + " player!";
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
//
//        String numTasksString = numTasks == 0 ? "0 tasks left over" : numTasks + " left over";
//
//        if (getSusLevel() > 15) {
//            numTasksString = numTasksString.toUpperCase();
//        }
//
//        return playerInfo.replace("not frozen", frozenString).replace("[numTasks]", numTasksString);
//    }


    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

}
