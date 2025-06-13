# [O]xxxxxx[[/\/\/\/\ DUST/\/\/\/\/\ >

* 🎮 It's a dungeon-crawling game ported to 3DS. I'd call it ___dustlike___, but it's roguelike.
* 🗡️ That single-line ASCII art above is a sword, and the name 'Dust' is carved on it.
* 🐡 [Dust copyright policy](https://github.com/Stagyrite/dust/blob/main/LICENSE "dust/LICENSE at main · Stagyrite/dust") states that you're free to use it, although it's not 100% copyleft.

## ["Contents"] | stdout

1. [Install](#install--stdout)
   * [Requires](#requires--stdout)
   * [Build](#build--stdout)
1. [Instructions](#instructions--stdout)
   * [Directions](#directions--stdout)
   * [Others](#others--stdout)
1. [Inspired by](#inspired-by--stdout)
1. [Story](#story--stdout)
1. [Jokes](#jokes--stdout)

### ["Install"] | stdout

#### ["Requires"] | stdout

* [Hardmodded Nintendo 3DS](https://repository-images.githubusercontent.com/778793386/dd003f0d-9b3d-4480-895c-d61aea643555 "OpenRoguelike ported to 3DS") or compatible
* [TwiLight Menu++ developed by Rocket Robz](https://wiki.ds-homebrew.com/twilightmenu/ "TWiLight Menu++ &#124; DS-Homebrew Wiki")
* [Pstros NDS](https://github.com/ole00/pstros-nds "ole00/pstros-nds: J2ME MIDP implementation")

#### ["Build"] | stdout

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

### ["Instructions"] | stdout

#### ["Directions"] | stdout

```
      up
     \|/
 left-@-right
     /|\
     down
```

#### ["Others"] | stdout

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
| c | 🐮 speaking cow |
| @ | player |

### ["Inspired", "by"] | stdout

* 🎮 [DSband](https://github.com/eltikia/openroguelike/discussions/6 "Nintendo 3DS port · eltikia/openroguelike · Discussion #6")
* 👺 [Eltikia/OpenRoguelike](https://github.com/eltikia/openroguelike "eltikia/openroguelike: The beginning of something that might end as a roguelike development library. But we'll see.")
* 🧩 [Maze](https://gist.github.com/Stagyrite/a72b22cd3c6ec16e6f33de589fa16ceb "a maze generator inspired by Maze")
* ⚛️ [Rogue](https://github.com/Davidslv/rogue "Davidslv/rogue: Original Rogue Game (5.4.4)")
* 🍅 [Hack](https://github.com/openbsd/src/tree/master/games/hack "src/games/hack at master · openbsd/src")
* 🎲 [NetHack](https://www.nethack.org/ "NetHack 3.6.7: NetHack Home Page")

### ["Story"] | stdout

```
 _________________________________________
/ The Fighters Guild is ready to be       \
| established in a ghost town. Because of |
| your position as a dungeon crawler,     |
| warrior-bard Ossian had ordered you to  |
| crawl down to level 99. He believes     |
| that your success can help              |
\ the Fighters Guild.                     /
 -----------------------------------------
        \   ^__^
         \  (..)\_______
            (__)\       )\/\
                ||----w |
                ||     |
```

### ["Jokes"] | stdout

![README Jokes](https://readme-jokes.vercel.app/api?theme=react)


```
# speaking Streem
menu1 = ["Contents", " Install", "  Requires", "  Build"]
menu2 = [" Instructions", "  Directions", "  Others"]
menu3 = ["Inspired by", "Story", "Jokes"]
menu = concat(menu1, concat(menu2, menu3))
menu | stdout
```

---
["𝑋"] \| stdout
