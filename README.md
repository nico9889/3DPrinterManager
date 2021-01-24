# 3DPrinterManager
Tired of watching my unreliable printer self-destroying while waiting the management Web Interface to load, I decided to write this little software.

This is a work in progress, so it's missing a lot of features and can be unstable.

## Current features
This software can show you:
* Printer status
* Print progress
* Bed and heaters temperatures (need proper testing, I cannot test multi-tool/multi-material printers)
* Alert dialog on print finish (not tested)
* Can manage multiple printer (cannot test, 3D printers are not that cheap)

## Upcoming features
* **EMERGENCY STOP** (basically the actual purpose of the software ¯\\\_(ツ)_/¯)
* Send GCode command (GCode console)
* Pause/Stop/Resume function
* Get/set printer position
* Enhanced stability/speed
---
* Macro support (*)
* Android Support (I'm trying to keep this software modular as much as I can so I can port it easily to Android, though I'm not an Android developer) (*)
* Marlin/Duet 3 support (I do not guarantee this, I don't actually need this and I can't test it since Duet 3 it's a really expensive board for me and I don't need another printer with Marlin firmware. I'll see what I can do...) (*)

(*) These features would be cool but I'm not sure I can do that.

Advanced features like file uploading, file management, printer calibration, etc are not planned. The idea it's to keep this **simple**, **fast** and possibly **reliable** and not to make a replacement to OctoPrint/Duet Web Control/Marlin Web GUI.

## Supported boards:
* Partial Duet 2 with Duet Web Control 1.x (it should work even on 2.x and 3.x)

## Development
This is my real first open source project that is not an experiment so I'm a bit unpractical with GitHub.
Do not expect a fast development or quick answer to issues/pull requests as I can be a little busy depending on period (I'm currently a University student).
