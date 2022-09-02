Breakout Game
===================

.. image:: ./static/images/2022-09-02_Breakout-Java.png
  :align: center

.. contents:: Table of Contents


Introduction
-------------

|Github Stars| |Github Forks| |Github Open Issues| |Github Open PRs|

**Breakout** is a Java implementation based off the arcade game,
`Breakout <https://en.wikipedia.org/wiki/Breakout_(video_game)>`_,
released in 1976.


Technologies Used
------------------

- Windows 10 x64
- Visual Studio Code
- Java 16


Project Status
---------------

This project is currently in **development**.


..
    Version Naming
    ---------------

    This library uses *semantic versioning*:

    .. code::

    MAJOR.MINOR.PATCH

    Where an increment in:

    - ``MAJOR`` = Incompatible changes (may require code to be updated).
    - ``MINOR`` = Backwards compatible feature changes.
    - ``PATCH`` = Backwards compatible bug fixes.


Getting Started
----------------

Compile the project and run ``Main.java``.

.. code:: console

    $ javac "./breakout/*.java" -d "./bin"
    $ java -cp "./bin" "./breakout/Main.java"


Contributing
-------------

Want to contribute? Great!

To fix a bug or enhance an existing module, follow these steps:

- `Fork <https://github.com/nicdgonzalez/Breakout/fork>`_ the repository
  and create a new branch:

.. code:: console

  $ git clone "https://github.com/[username]/[repository_name].git"
  $ git checkout -b "improve-feature"

- Make the appropriate changes and stage the modified files:

.. code:: console

  $ git add <changed file(s)>

- Commit changes:

.. code:: console

  $ git commit -m "Improve feature."

- Push to the new branch:

.. code:: console

  $ git push "origin" "improve-feature"

- Create a `Pull Request <https://github.com/nicdgonzalez/Breakout/pulls>`_.


Bug/Feature Request
--------------------

If you find a bug (program failed to run and/or gave undesired results)
or you just want to request a feature, kindly open a new issue
`here <https://github.com/nicdgonzalez/Breakout/issues>`_.


Room for Improvement
---------------------

- When the ball hits the side of a block, there is occasionally strange
  behaviour that occurs where the ball goes upwards when it should be
  falling. This results in the ball knocking into a bundle of blocks.

- Pressing ENTER resets the game even while you are playing. I would
  like to make it only work on the screen prompting the player to hit
  ENTER (after either winning or loosing). Winning/Losing message is
  also not centered.

- The ball should accelerate at a decent rate as the game is played to
  make it a bit more interesting and faster-paced.

- Maps should get increasingly more difficult as they are completed.
  Currently, there are only two maps: the initial one and the one when
  the player hits ENTER.

- The border is incorrect on the right side. The edge seems to be
  off the screen by a few pixels.

- The ball should start from the center and go a random direction on
  "kickoff". The initial move is always the same, taking out the same
  three blocks at the start, I would like to change that.


Acknowledgements
-----------------

Base implementation was a rewrite of `awaismirza/Java-Game-Brick-Breaker`_.
Thank you for sharing your code so newbies like me can learn to write in Java!

..
  ****************************************************************************
.. |Github Stars| image:: https://badgen.net/github/stars/nicdgonzalez/Breakout
.. |Github Forks| image:: https://badgen.net/github/forks/nicdgonzalez/Breakout
.. |Github Open Issues| image:: https://badgen.net/github/open-issues/nicdgonzalez/Breakout
  :target: https://github.com/nicdgonzalez/Breakout/issues?q=is%3Aissue+is%3Aopen+
.. |Github Open PRs| image:: https://badgen.net/github/open-prs/nicdgonzalez/Breakout
  :target: https://github.com/nicdgonzalez/Breakout/pulls?q=is%3Apr+is%3Aopen+

.. _awaismirza/Java-Game-Brick-Breaker: https://github.com/awaismirza/Java-Game-Brick-Breaker
