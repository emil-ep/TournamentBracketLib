# TournamentBracketLib
A library to generate tournament brackets with smooth transitions and animations.

https://user-images.githubusercontent.com/32976134/122669203-d7966c00-d1d9-11eb-8471-e88af082aeb3.mp4


## Requirements
- Android 7.0 (API 24) and above

## Installation

Add the JitPack repository to your project-level `build.gradle`:
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency:
```gradle
dependencies {
    implementation 'com.github.emil-ep:TournamentBracketLib:1.1.4'
}
```

## Setup

Add `BracketsView` to your XML layout:
```xml
<com.ventura.bracketslib.BracketsView
    android:id="@+id/bracket_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:bracketBackgroundColor="#1c222e"
    app:bracketColor="#262e40"
    app:bracketTextColor="#fff" />
```

Get a reference in code and supply data:
```java
BracketsView bracketsView = findViewById(R.id.bracket_view);
bracketsView.setBracketsData(Arrays.asList(semiFinalColumn, finalColumn));
```

## Data Model

### `CompetitorData`
Represents a single team or player.

| Field | Type | Description |
|-------|------|-------------|
| `name` | `String` | Competitor name (constructor param) |
| `score` | `String` | Score to display (constructor param) |
| `imageUrl` | `String` | URL of a flag or club logo — shown as a circular image. Set to `null` or omit to hide. |

```java
CompetitorData brazil = new CompetitorData("Brazil", "3");
brazil.setImageUrl("https://example.com/brazil.png"); // optional
```

---

### `MatchData`
Represents a match between two competitors.

| Field | Type | Description |
|-------|------|-------------|
| `competitorOne` | `CompetitorData` | First competitor (constructor param) |
| `competitorTwo` | `CompetitorData` | Second competitor (constructor param) |
| `matchName` | `String` | Label shown above the match cell (e.g. "Quarter Final 1"). Set to `null` or omit to hide. |

```java
MatchData match = new MatchData(brazil, england);
match.setMatchName("Semi Final 1"); // optional
```

---

### `ColomnData`
Represents a round column (e.g. Quarter Finals, Semi Finals, Final). Takes a list of `MatchData`.

```java
ColomnData semiFinal = new ColomnData(Arrays.asList(match1, match2));
ColomnData final_  = new ColomnData(Arrays.asList(matchFinal));
```

---

## Full Example

```java
// Competitors
CompetitorData brazil    = new CompetitorData("Brazil", "3");
CompetitorData england   = new CompetitorData("England", "1");
CompetitorData argentina = new CompetitorData("Argentina", "3");
CompetitorData russia    = new CompetitorData("Russia", "2");
CompetitorData brazilF   = new CompetitorData("Brazil", "4");
CompetitorData argentinaF = new CompetitorData("Argentina", "2");

// Optional: set image URLs
brazil.setImageUrl("https://example.com/brazil.png");
england.setImageUrl("https://example.com/england.png");

// Matches
MatchData match1 = new MatchData(brazil, england);
match1.setMatchName("Semi Final 1");

MatchData match2 = new MatchData(argentina, russia);
match2.setMatchName("Semi Final 2");

MatchData matchFinal = new MatchData(brazilF, argentinaF);
matchFinal.setMatchName("Final");

// Columns
ColomnData semiFinals = new ColomnData(Arrays.asList(match1, match2));
ColomnData finals     = new ColomnData(Arrays.asList(matchFinal));

// Set data
bracketsView.setBracketsData(Arrays.asList(semiFinals, finals));
```

## XML Attributes

| Attribute | Description |
|-----------|-------------|
| `app:bracketBackgroundColor` | Background color of the whole bracket view |
| `app:bracketColor` | Background color of each match cell row |
| `app:bracketTextColor` | Text color for competitor names and scores |

## Find this project useful? ❤️
Support it by leaving a ⭐️ at the top of this page.
