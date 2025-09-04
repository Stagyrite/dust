# Dust

* 🎮 It's a dungeon-crawling game ported to 3DS. I'd call it ___dustlike___, but it's roguelike.
* 🎲 Inspired by [DSband](https://github.com/eltikia/openroguelike/discussions/6 "Nintendo 3DS port · eltikia/openroguelike · Discussion #6") (an [OpenRoguelike](https://github.com/eltikia/openroguelike "eltikia/openroguelike: The beginning of something that might end as a roguelike development library. But we'll see.") port to softmodded 3DS).
* 🐡 [Dust copyright policy](https://github.com/Stagyrite/dust/blob/main/LICENSE "dust/LICENSE at main · Stagyrite/dust") states that you're free to use it, although it's not 100% copyleft.

## Contents

1. [Install](#install)
   * [Requires](#requires)
   * [Build](#build)
1. [Instructions](#instructions)
   * [Directions](#directions)
   * [Others](#others)
1. [Story](#story)
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
the Dust.class file with TwiLight Menu++ and the game
is all for you.

### Instructions

#### Directions

```
      up
     \|/
 left-@-right
     /|\
     down
```

#### Others

| Key | Action |
| :--- | ---: |
| A | climb up or down |
| Select | swap the LCD screen |
| Start | __game over__ |

| Tile | Type |
| :--- | ---: |
| &gt; | stairs down |
| < | stairs up |
| = | wall |
| + | door |
| c | speaking cow |
| @ | player |

### Story

```
 _________________________________________
/ The Fighters Guild is ready to be       \
| established in a ghost town. Because of |
| your position as a dungeon crawler,     |
| warrior-bard Ossian had ordered you to  |
| crawl down to level 99. He believes     |
| that your success can help              |
\ the Fighters Guild. -- The Speaking Cow /
 -----------------------------------------
        \   ^__^
         \  (..)\_______
            (__)\       )\/\
                ||----w |
                ||     |
```

### Jokes

![README Jokes](https://readme-jokes.vercel.app/api?theme=react)

---

⚔️🛡️🏹🗡️

