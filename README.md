# dust

👺 [rogue](https://github.com/Davidslv/rogue "Davidslv/rogue: Original Rogue Game (5.4.4)") ≺ [hack](https://github.com/openbsd/src/tree/master/games/hack "src/games/hack at master · openbsd/src") ≺ [dust](#dust)

## Inspired by

1. [eltikia/openroguelike](https://github.com/eltikia/openroguelike "eltikia/openroguelike: The beginning of something that might end as a roguelike development library. But we'll see.")
1. [maze](https://gist.github.com/Stagyrite/a72b22cd3c6ec16e6f33de589fa16ceb "a maze generator inspired by Maze")

## Contents

1. [Story](#story)
1. [Install](#install)
1. [Instructions](#instructions)
1. [Jokes](#jokes)

## Story

```
 _________________________________________
/ The Fighter's Guild is ready to be      \
| established in a ghost town. Because of |
| your position as a dungeon crawler,     |
| Lord Kiron had ordered you to crawl     |
| down to level 99. Lord Kiron believes   |
| that your success can help the          |
\ Fighter's Guild.                        /
 -----------------------------------------
        \   ^__^
         \  (..)\_______
            (__)\       )\/\
                ||----w |
                ||     |
```

## Install

### Requires

* Nintendo 3DS or compatible
* [TwiLight Menu++ developed by Rocket Robz](https://wiki.ds-homebrew.com/twilightmenu/ "TWiLight Menu++ | DS-Homebrew Wiki")
* [Pstros NDS](https://github.com/ole00/pstros-nds "ole00/pstros-nds: J2ME MIDP implementation")
* A computer to transfer files to a microSD card

### Build

To build from sources, you have to install
[J2ME Wireless Toolkit 2.2](https://www.oracle.com/java/technologies/java-archive-downloads-javame-downloads.html "Java Archive Downloads - Java ME"),
as well as the
[J2SE(TM) Development Kit 5.0 Update 6](https://www.oracle.com/pl/java/technologies/java-archive-javase5-downloads.html "Java Archive Downloads - Java SE 5 | Oracle Polska").
Run build.bat and it should output binaries to the 'output' directory.

To install, copy the content of the 'output' directory
to your 3DS. The destination directory has to have the 'nds'
subdirectory, e.g. roms/nds/Pstros072nds/classes. Now load
the FighersGuild.class file with TwiLight Menu++ and the game
is all for you.

## Instructions

Directions:

```
      up
     \|/
 left-@-right
     /|\
     down
```

Others:

* `A`: climb to the next or the previous level ;
* `Select`: swap the LCD screen ;
* `Start`: die ;

- &gt; stairs down
- < stairs up
- '=' wall
- '+' door
- 'c' cow
- '@' player

## Jokes

![README Jokes](https://readme-jokes.vercel.app/api?theme=react)

