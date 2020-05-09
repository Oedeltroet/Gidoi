package localisation;

import java.util.ListResourceBundle;

public class Localisation extends ListResourceBundle {
	
	@Override
	protected Object[][] getContents() {
		
		return new String[][] {
			
			{"FILE", "File"},
			{"NEW", "New"},
			{"SAVE", "Save"},
			{"OPEN", "Open"},
			{"EXPORT", "Export"},
			{"SETTINGS", "Settings"},
			{"QUIT", "Quit"},
			{"SCREENPLAY", "Screenplay"},
			{"SCREENPLAY_EDITOR", "Screenplay Editor"},
			
			{"DLG_ERROR", "Error"},
			{"DLG_OPEN_FILE", "Open File"},
			{"DLG_NEW_ACTION", "New Action"},
			{"DLG_EDIT_ACTION", "Edit Action"},
			{"DLG_NEW_CHARACTER", "New Character"},
			{"DLG_NEW_DIALOGUE", "New Dialogue"},
			{"DLG_EDIT_DIALOGUE", "Edit Dialogue"},
			{"DLG_NEW_LOCATION", "New Location"},
			{"DLG_NEW_SCENE", "New Scene"},
			{"DLG_NEW_SCREENPLAY", "New Screenplay"},
			{"DLG_SCENE_EDITOR", "Scene Editor"},
			{"DLG_SCREENPLAY_EDITOR", "Screenplay Editor"},
			{"DLG_SETTINGS", "Settings"},
			{"DLG_WARNING", "Warning"},
			
			{"ERR_ADD_ELEMENT", "Due to an error, the element could not be added.\nFor further information, please check the error log and contact the developer."},
			{"ERR_CREATE_SCREENPLAY", "Due to an error, the screenplay could not be created.\nFor further information, please check the error log and contact the developer."},
			{"ERR_EXPORT", "An error occurred during export.\nFor further information, please check the error log and contact the developer."},
			{"ERR_INIT", "An error occured during intialisation.\nPlease check the error log and contact the developer."},
			{"ERR_INSERT_SCENE", "Due to an error, the created scene could not be inserted correctly.\nFor further information, please check the error log and contact the developer."},
			{"ERR_INVALID_FILE", "This file is invalid.\nFor further information, please check the validation log."},
			{"ERR_INVALID_FILENAME", "Please enter a valid filename."},
			{"ERR_LOAD_SCREENPLAY", "Due to an error, the screenplay could not be loaded.\nFor further information, please check the error log and contact the developer."},
			{"ERR_LOAD_SETTINGS", "Due to an error, user settings could not be loaded.\nMake sure that a file called 'settings' exists in the root directory.\nIf that is the case, please check the error log and contact the developer."},
			{"ERR_PREVIEW", "Due to an error, the preview could not be refreshed.\nFor further information, please check the error log and contact the developer."},
			{"ERR_REMOVE_ELEMENT", "Due to an error, the element could not be removed.\nFor further information, please check the error log and contact the developer."},
			{"ERR_SAVE_SCREENPLAY", "Due to an error, the screenplay could not be saved.\nFor further information, please check the error log and contact the developer."},
			{"ERR_SAVE_SETTINGS", "Due to an error, user settings could not be saved.\nMake sure that a file called 'settings' exists in the root directory.\nIf that is the case, please check the error log and contact the developer."},
			{"ERR_VALIDATION_FAILED", "An error occured during validation process.\nPlease check the error log and contact the developer."},
			{"ERR_XML_SCHEMA_FILE", "Due to an error, the XML schema file could not be loaded.\nThis may cause trouble when trying to load screenplays.\nFor further information, please check the error log and contact the developer."},
			
			{"WRN_OVERWRITE", "A screenplay with this name already exists.\nWould you like to overwrite it?"},
			{"WRN_UNSAVED_CHANGES", "You have unsaved changes.\nWould you like to save them?"},
			{"WRN_ARE_YOU_SURE", "Are you sure?"},
			{"WRN_LOCATION_EXISTS", "A location with this name already exists."},
			{"WRN_CHARACTER_EXISTS", "A character with this name already exists."},
			
			{"BTN_ADD", "Add"},
			{"BTN_ADD_ACTION", "Add Action"},
			{"BTN_ADD_DIALOGUE", "Add Dialogue"},
			{"BTN_APPLY", "Apply"},
			{"BTN_EDIT", "Edit"},
			{"BTN_OK", "OK"},
			{"BTN_REMOVE", "Remove"},
			
			{"PNL_PROPERTIES", "Properties"},
			{"PNL_LOCATION", "Location"},
			{"PNL_PLACE", "Place"},
			{"PNL_TIME", "Time"},
			
			{"TAB_GENERAL", "General"},
			{"TAB_SCENES", "Scenes"},
			{"TAB_LOCATIONS", "Locations"},
			{"TAB_CHARACTERS", "Characters"},
			
			{"DFT_CHARACTER", "New Character"},
			{"DFT_LOCATION", "New Location"},
			{"DFT_TITLE", "New Screenplay"},
			
			{"TT_PROPERTIES_TITLE", "Choose a title for this screenplay. It can be changed later."},
			{"TT_PROPERTIES_AUTHOR", "Set the name of the author of this screenplay. It can be changed later."},
			{"TT_PROPERTIES_FILENAME", "Choose a name for the screenplay file. Note that it may not contain periods or shlashes and the extension must be '.xml'"},
			{"TT_PROPERTIES_LOCATION", "Choose a location for this scene. Usage is required."},
			{"TT_PROPERTIES_MOD_LOCATION", "Modify the location for this scene. Usage is optional."},
			{"TT_PROPERTIES_PLACE", "Define where the scene takes place. Usage is optional but recommended."},
			{"TT_PROPERTIES_TIME", "Select the daytime of this scene. Usage is optional but recommended."},
			{"TT_CREATE_SCENE_APPEND", "Create a new scene and append it to the screenplay."},
			{"TT_CREATE_SCENE_INSERT", "Create a new scene and insert it before the selected scene."},
			{"TT_CREATE_SCENE_NO_LOCATION", "You need to create a location first."},
			{"TT_EDIT_SCENE", "Open the Scene Editor to edit the selected scene."},
			{"TT_EDIT_SCENE_NOTHING_SELECTED", "Select the scene you want to edit."},
			{"TT_MOVE_SCENE_UP", "Move the selected scene towards the beginning."},
			{"TT_MOVE_SCENE_UP_ALREADY_FIRST", "This is the first scene."},
			{"TT_MOVE_SCENE_DOWN", "Move the selected scene towards the end."},
			{"TT_MOVE_SCENE_DOWN_ALREADY_LAST", "This is the last scene."},
			{"TT_MOVE_SCENE_NOTHING_SELECTED", "No scene selected."},
			{"TT_REMOVE_SCENE", "Remove the selected scene from the screenplay."},
			{"TT_REMOVE_SCENE_NOTHING_SELECTED", "Select the scene you want to remove."},
			{"TT_ADD_LOCATION", "Create a new location for the screenplay."},
			{"TT_REMOVE_LOCATION", "Remove the selected location from the screenplay."},
			{"TT_REMOVE_LOCATION_USED_FOR_SCENE", "The selected location cannot be removed, since it is being used for a scene."},
			{"TT_REMOVE_LOCATION_NOTHING_SELECTED", "Select the location you want to remove."},
			{"TT_ADD_CHARACTER", "Create a new character for the screenplay."},
			{"TT_REMOVE_CHARACTER", "Remove the selected character from the screenplay."},
			{"TT_REMOVE_CHARACTER_USED_FOR_DIALOGUE", "The selected character cannot be removed, since it has at a line of dialogue in scene "},
			{"TT_REMOVE_CHARACTER_NOTHING_SELECTED", "Select the character you want to remove."},
			{"TT_CREATE_ACTION_APPEND", "Create a new action and append it to the scene."},
			{"TT_CREATE_ACTION_INSERT", "Create a new action and insert it before the selected element."},
			{"TT_CREATE_DIALOGUE_APPEND", "Create a new dialogue and append it to the scene."},
			{"TT_CREATE_DIALOGUE_INSERT", "Create a new dialogue and insert it before the selected element."},
			{"TT_CREATE_DIALOGUE_NO_CHARACTER", "You need to create a character first."},
			{"TT_EDIT_ACTION", "Edit the selected action."},
			{"TT_EDIT_DIALOGUE", "Edit the selected dialogue."},
			{"TT_EDIT_SCENE_ELEMENT_NOTHING_SELECTED", "Select the scene element you want to edit."},
			{"TT_MOVE_SCENE_ELEMENT_UP", "Move the selected scene element towards the beginning."},
			{"TT_MOVE_SCENE_ELEMENT_UP_ALREADY_FIRST", "This is the first scene element."},
			{"TT_MOVE_SCENE_ELEMENT_DOWN", "Move the selected scene element towards the end."},
			{"TT_MOVE_SCENE_ELEMENT_DOWN_ALREADY_LAST", "This is the last scene element."},
			{"TT_MOVE_SCENE_ELEMENT_NOTHING_SELECTED", "No scene element selected."},
			{"TT_REMOVE_SCENE_ELEMENT", "Remove the selected element from the scenen."},
			{"TT_REMOVE_SCENE_ELEMENT_NOTHING_SELECTED", "Select the scene element you want to remove."},
		};
	}
}