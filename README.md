# Gidoi
 Gidoi is an open source screenplay editor.
 
## Prerequisites
What you need to get before using the program.
* [Java](https://www.java.com/download/)

## Installation
Just click on _Gidoi.exe_ (Windows) or _Gidoi.jar_ and the application should initialize itself. This may take a few moments.

## Getting started
* After the first startup, you should see the main window containing a blank preview at the center as well as a toolbar at the top and a status bar at the bottom. The root directory should contain the following:
  * _logs_ - a directory which contains the logfiles _error.txt_ and _validation.txt_
  * _screenplays_ - the directory in which your screenplays will be stored as _.xml_ files
  * _screenplay.xsd_ - a file which contains the xml schema for your screenplays
  * _settings_ - the file in which the user settings are stored

* To create a new screenplay, click on the _New_ button in the top left corner. This will open a dialog with three text fields, which allow you to choose a title, set the author's name and the name of the file. The latter is automatically adapted to the title, so unless you want to, you don't have to change it manually. However, if you do so, mind the restrictions given by the tooltip. Finally you can create your new screenplay by clicking on the _OK_ button.

* After creating the screenplay, the next thing you want to do is add your first scene. To do that, you have to open the screenplay editor via the corresponding button, which is located right at the top. A new dialog will open, containing two panels. The upper one contains the screenplay properties, which you are already familiar with. The lower one consists of three tabs: _Scenes_, _Locations_ and _Characters_. The _Scenes_ tab is already selected. You will notice, that the _Add_ button is greyed out, signalising that it can't be used at the moment. If you hover it with the cursor, the tooltip will tell you that you have to create a location first. So switch to the _Locations_ tab and click on the _Add_ button, which will open a dialog containing a text field. Give the new location a name and click on _OK_. It will then appear in the location list on the left. Now you can switch back to the _Scenes_ tab and click on the now enabled _Add_ button. In the following dialog you will have a number of properties to set and finally confirm by clicking on _OK_.
  * _Location_ - Pick a location for the scene from the screenplay's list of locations. Since the one you just created is currently the only one, you won't be able to change anything yet.
  * _Location Modifier_ - Here you can specify the location, e.g. if the scene took place only at a specific part of the location, you would state that here.
  * _Place_ - Is this an interior or exterior scene or both?
  * _Time_ - At which time of day does the scene take place?

## Development tools
* IDE: [Eclipse](https://www.eclipse.org/)
* Build tool: [Maven](https://maven.apache.org/)
* PDF tool: [PDFBox](https://pdfbox.apache.org/)

## License
This project is licensed under the [MIT license](https://choosealicense.com/licenses/mit/). For further information check the [LICENSE.md](LICENSE.md) file.

## Motivation
The main purpose of this project is to create a software that supports screenwriters by taking over repetitive work and thereby making more room for the creative process.
 
## Goals
The following goals represent the desired characteristics of the program.
* __Complexity__ - May it be more than a simple editor.
* __Universality__ - May it be useful for any kind of screenplay.
* __Intuitivity__ - May it be easy to use for everyone.

## Authors
* Teo Förste

## Appreciation
Many thanks to all testers who have significantly contributed to the development of this software through their detailed and constructive feedback.
