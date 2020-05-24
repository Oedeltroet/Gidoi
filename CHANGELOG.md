# Gidoi - Changelog

In this file, all notable changes to the project are documented and commented by the developer.

## Version 0.3 (Beta)

2020-05-24

### Features

* #### File Chooser
  Instead of being limited to default paths, you can now save, load and export files via file chooser - a special kind of window which allows you to browse the file system and choose any destination you like, the only restriction being the file extension: _.xml_ for screenplay files and _.pdf_ for exported screenplays.

* #### Settings
  Besides some GUI improvements, the settings window now features an option to enable or disable automatic updates.

* #### Window Layout
 * As requested, the Screenplay and Scene Editor windows are now resizable.
 * Position and size of the main window will be saved in the user settings and restored on the next startup.
 * The method that centers a window based on it's parent's bounds has been improved and should now work correctly.
 
### Files

* Updated [Readme file](README.md)
* Default paths _screenplays/_ and _exported/_ are obsolete and will henceforth be ignored. However, if they are already in use, they are still accessible via the file chooser. Existing files will not be deleted or altered by the new version.
* Due to code improvement, the file _src/LanguageSelectionListener.java_ is no longer used and can be deleted.

### Bugfixes and minor changes

* Settings: fixed language selection bug
* Preview: added missing space after int./ext.
* Exporter: added page numbering
* Formatting: implemented method to remove new line characters which cause formatting errors
* Localisation: minor changes, added missing tool tips.



## Version 0.2 (Beta)

2020-05-17

### Features

* #### Updater
  Gidoi's first major update is all about the Updater. This new feature automatically checks if an update is available and offers the user to download and install it. To make that possible, the program must be constantly located in a cloned git repository. However, git is _not_ required to run the program, since everything is handled by the program itself, using a library called [JGit](https://www.eclipse.org/jgit/). To repair or update the repository, the latest version will be cloned into a temporary folder, whose contents will then be moved up to replace the current files. Since the _.jar_ file cannot be replaced at runtime, those operations will be carried out by temporary, platform specific script files.
* #### Splash Screen
  Loading times can vary and, depending on numerous factors, take from just a few moments up to several minutes. To show and assure the user that the program is not inactive, a splash screen is now visualising the loading process.
* #### Mac OS & Linux compatibility
  When it comes to script-executed file operations, we leave Java's compfortable platform independent environment and need some platform specific code. And as for that, the three major operating systems vary in quite a few aspects.

### Files

* Added [License file](LICENSE)
* Updated [Readme file](README.md)
* Removed unnecessary files:

 * _bin/_, _.target/_ and _Thumbs.db_, since they are automatically generated
 * _.project_, _.settings/_ and _.classpath_, since they are Eclipse specific
 * _Gidoi.exe_, since it is a large, redundant, Windows specific file
 
### Bugfixes and minor changes
 
* Corrected typo _Giodi.jar_ -> _Gidoi.jar_