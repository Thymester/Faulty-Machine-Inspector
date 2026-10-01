# Faulty Machine Inspector

Faulty Machine Inspector is a Java console game about diagnosing and repairing malfunctioning machines. Each round presents an inspection report for a randomly selected machine and randomly marks one of its components as faulty. The player must enter the exact component name to repair the machine, while managing hints, penalties, score, and streaks.

The current game version is `0.3.0`.

## Requirements

- Java Development Kit (JDK) with support for text blocks and switch arrow labels. JDK 17 or newer is recommended.
- A terminal capable of interactive standard input.

The project uses only the Java standard library. It has no external dependencies, build tool configuration, or third-party packages.

## Project Structure

```text
Faulty Machine Inspector/
├── FaultyMachine.java          # Application entry point
├── Machines/
│   ├── Component.java           # Component fault and repair state
│   ├── Machine.java             # Shared machine behavior and game statistics
│   ├── CoolingMachine.java      # Cooling machine implementation
│   ├── HeatingMachine.java      # Heating machine implementation
│   └── PowerMachine.java        # Power machine implementation
└── UI/
    ├── ConsoleUI.java           # Main menu and interactive game loop
    ├── LearnHowPlay.java        # How-to-play text
    └── UpcomingFeatures.java    # Upcoming-features text
```

## Compile and Run

Run these commands from the project root, the directory containing `FaultyMachine.java`:

```bash
mkdir -p build
javac -d build FaultyMachine.java Machines/*.java UI/*.java
java -cp build FaultyMachine
```

The `-d build` option places compiled classes into a directory that matches the Java package structure. The source files remain unchanged.

To remove the generated build output:

```bash
rm -rf build
```

## Starting the Game

When the program starts, it displays the main menu:

```text
1. Play Game
2. Learn How to Play
3. Future Features
4. Exit Game
```

Menu input must be an integer from `1` through `4`. Other input is rejected and the program asks for a valid selection.

### Play Game

Selecting `1` starts a seven-round game. At the beginning of each round, the program randomly chooses one of these machines:

- **Cooling Machine**
  - `fan`
  - `coolant pump`
- **Power Machine**
  - `battery`
  - `generator`
- **Heating Machine**
  - `fan`
  - `furnace`

Each machine has exactly one faulty component. The faulty component is selected randomly when the machine is created.

The program then prints an inspection report. For the first three levels, the report includes a `Possible problem areas` line naming the selected component. From level 3 onward, that line is omitted from the report. The player can still use the `help` command to reveal the faulty component.

At the repair prompt, enter the component name exactly as shown above. Input is converted to lowercase before it is checked, so capitalization does not matter. Spelling and spacing do matter.

Available commands and inputs are:

| Input | Result |
| --- | --- |
| A component name | Repairs the component if it is the faulty one; otherwise applies the wrong-component penalty. |
| `help` | Prints a hint naming the faulty component, applies the hint penalty, increases hint usage, and resets the streak. |
| `quit` | Prints a goodbye message and exits immediately. |

After a correct repair, the game prints the current level, score, maximum possible score, and current streak. The next round begins after the repaired machine becomes operational.

## Scoring and Progression

The values implemented in `Machine.java` and `ConsoleUI.java` are:

- Starting score: `0`.
- Correct repair: `+100` points.
- Wrong component: `-5` points.
- Hint: `-20` points in the implementation.
- Maximum score value displayed: `700`.
- Correct repair streak: increases by `1`.
- Hint use: resets the current streak to `0`.
- Hint limit: `3` uses.
- Total rounds: `7`.
- Level: increases after each correct repair.

The score, level, hint usage, and streak counters are static fields on `Machine`, so they are shared by all machine instances during the running Java process. The game loop ends after the seven-round sequence or immediately when `quit` is entered.

## Learn How to Play

Selecting `2` prints the instructions stored in `UI/LearnHowPlay.java`. Those instructions describe the same main concepts: scoring, exact component names, streaks, hints, and the change from reports with possible problem areas to reports without them.

One displayed instruction says that a hint costs `-25` points. The current implementation in `Machine.setHelpPointPenalty()` subtracts `20` points, so the implementation is the authoritative behavior when playing.

## Future Features Screen

Selecting `3` prints the list stored in `UI/UpcomingFeatures.java`:

1. Better hints without using the hint command.
2. More machine types.
3. Top streak tracking.
4. Top score tracking.
5. More rounds to increase the top score and streak.
6. Miscellaneous bug fixes after the listed critical bugs were fixed.

This option displays text only; it does not enable additional features in the current build.

## Implementation Details

`FaultyMachine.java` calls `ConsoleUI.mainMenu()`, which owns the interactive console loop. `ConsoleUI` creates machine objects and chooses the machine type using a random number from `0` through `25`:

- Values `0` through `7` create a `CoolingMachine`.
- Values `8` through `15` create a `PowerMachine`.
- Values `16` through `25` create a `HeatingMachine`.

Each concrete machine extends the abstract `Machine` class. The base class provides component storage, random fault selection, operational status checks, scoring, level tracking, hints, and streak tracking. Each concrete class supplies its component names, repair messages, and inspection report.

`Component.isWorking()` returns `true` when a component is not faulty or has been repaired. A machine is operational only when every component passes that check.

## Current Behavior Notes

- The game accepts component names case-insensitively because input is lowercased before comparison.
- Component names are not presented as a separate command list during the repair prompt.
- A wrong component name applies the penalty but does not end the round.
- A hint does not repair the machine and cannot be used after the three-use limit is reached.
- The source includes an existing compiled `FaultyMachine.class` in the project directory, but compiling to `build/` as shown above keeps generated output separate from the source tree.

## License

Program is licensed under the MIT license.
