# Dust

* 🎮 It's a __roguelike__ dungeon-crawling game ported to 3DS.
* 🎲 Inspired by [DSband](https://github.com/eltikia/openroguelike/discussions/6 "Nintendo 3DS port · eltikia/openroguelike · Discussion #6") (an [OpenRoguelike](https://github.com/eltikia/openroguelike "eltikia/openroguelike: The beginning of something that might end as a roguelike development library. But we'll see.") port to softmodded 3DS).
* 🐡 [Dust copyright policy](https://github.com/Stagyrite/dust/blob/main/LICENSE "dust/LICENSE at main · Stagyrite/dust") states that you're free to use it, although it's not 100% copyleft.
* ☕ Check out a similar level compiler that I called [LibreRoguelike](https://gist.github.com/Stagyrite/921e5e48465e92ee0fe829b4e32b2c05 "🦇 a level compiler inspired by openroguelike").

## Contents

1. [Install](#install)
   * [Requires](#requires)
   * [Build](#build)
1. [Instructions](#instructions)
   * [Directions](#directions)
   * [Others](#others)
1. [Two cows say](#two-cows-say)
1. [Jokes](#jokes)

### Install

#### Requires

* [Hardmodded Nintendo 3DS](https://repository-images.githubusercontent.com/778793386/dd003f0d-9b3d-4480-895c-d61aea643555 "OpenRoguelike ported to 3DS") or compatible
* [TwiLight Menu++ developed by Rocket Robz](https://wiki.ds-homebrew.com/twilightmenu/ "TWiLight Menu++ &#124; DS-Homebrew Wiki")
* [Pstros NDS](https://github.com/ole00/pstros-nds "ole00/pstros-nds: J2ME MIDP implementation")

#### Build

To build from sources, you have to install
[J2ME Wireless Toolkit 2.2](https://www.oracle.com/java/technologies/java-archive-downloads-javame-downloads.html "Java Archive Downloads - Java ME"),
as well as the
[J2SE(TM) Development Kit 5.0 Update 6](https://www.oracle.com/pl/java/technologies/java-archive-javase5-downloads.html "Java Archive Downloads - Java SE 5 | Oracle Polska").
Run build.bat, which should output binaries to the 'output' directory.

To install, copy the content of the 'output' directory to the microSD card
from your 3DS. The destination directory has to have the 'nds'
subdirectory, e.g. roms/nds/Pstros072nds/classes. Now load
the Dust.class file with TwiLight Menu++, and the game
is all for you.

### Instructions

#### Directions

```
  NW N NE
    \|/
   W-@-E
    /|\
  SW S SE
```

#### Others

| Key | Action |
| :--- | ---: |
| A | climb up or down |
| Select | swap the LCD screen |
| Start | quit |

| Tile | Type |
| :--- | ---: |
| &gt; | stairs down |
| < | stairs up |
| = | wall |
| + | door |
| c | cow |
| @ | player |

### Two cows say

```
 _________________________________________
/ Game master: Warrior-bard Ossian        \
| Player: A dungeon crawler               |
| Goal: Dungeon level 99                  |
\ Start: The Fighters Guild in Ghost Town /
 -----------------------------------------
   \   ^__^                     (__)   /
    \  (..)\_______             (oo)  /
       (__)\       )\/\   /------\/
           ||----w |     / |    ||
           ||     |     *  /\---/\
           ~~    ~~        ~~   ~~
```

### Jokes

![README Jokes](https://readme-jokes.vercel.app/api?theme=react)

---

⚔️🛡️🏹🗡️

