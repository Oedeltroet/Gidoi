package localisation;

import java.util.ListResourceBundle;

public class Localisation_de extends ListResourceBundle {
	
	@Override
	protected Object[][] getContents() {
		
		return new String[][] {
			
			{"NEW", "Neu"},
			{"SAVE", "Speichern"},
			{"OPEN", "�ffnen"},
			{"EXPORT", "Exportieren"},
			{"SETTINGS", "Einstellungen"},
			{"SCREENPLAY_EDITOR", "Drehbucheditor"},
			
			{"DLG_ERROR", "Fehler"},
			{"DLG_OPEN_FILE", "Datei �ffnen"},
			{"DLG_NEW_ACTION", "Handlung erstellen"},
			{"DLG_EDIT_ACTION", "Handlung bearbeiten"},
			{"DLG_NEW_CHARACTER", "Charakter erstellen"},
			{"DLG_NEW_DIALOGUE", "Dialog erstellen"},
			{"DLG_EDIT_DIALOGUE", "Dialog bearbeiten"},
			{"DLG_NEW_LOCATION", "Schauplatz erstellen"},
			{"DLG_NEW_SCENE", "Szene erstellen"},
			{"DLG_NEW_SCREENPLAY", "Drehbuch erstellen"},
			{"DLG_SCENE_EDITOR", "Szeneneditor"},
			{"DLG_SCREENPLAY_EDITOR", "Drehbucheditor"},
			{"DLG_SETTINGS", "Einstellungen"},
			{"DLG_UPDATE", "Update"},
			{"DLG_WARNING", "Warnung"},
			
			{"ERR_ADD_ELEMENT", "Aufgrund eines Fehlers konnte das Element nicht hinzugef�gt werden.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_CREATE_SCREENPLAY", "Aufgrund eines Fehlers konnte das Drehbuch nicht erstellt werden.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_EXPORT", "Beim Exportvorgang ist ein Fehler aufgetreten.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_INIT", "Bei der Initialisierung der Anwendung ist ein Fehler aufgetreten.\nBitte konsultieren Sie das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_INSERT_SCENE", "Aufgrund eines Fehlers konnte die erstellte Szene nicht richtig eingef�gt werden.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_INVALID_FILE", "Diese Datei ist fehlerhaft.\nF�r weitere Informationen konsultieren Sie bitte das Validierungsprotokoll."},
			{"ERR_INVALID_FILENAME", "Bitte geben Sie einen g�ltigen Dateinamen an."},
			{"ERR_LOAD_SCREENPLAY", "Aufgrund eines Fehlers konnte das Drehbuch nicht geladen werden.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_LOAD_SETTINGS", "Aufgrund eines Fehlers konnten keine benutzerdefinierten Einstellungen geladen werden.\nVergewissern Sie sich, dass eine Datei mit dem Namen 'settings' im Hauptverzeichnis existiert.\nWenn das der Fall ist, konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_PREVIEW", "Beim Aktualisieren der Vorschau ist ein Fehler aufgetreten.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_REMOVE_ELEMENT", "Aufgrund eines Fehlers konnte das Element nicht entfernt werden.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_SAVE_SCREENPLAY", "Aufgrund eines Fehlers konnte das Drehbuch nicht gespeichert werden.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_SAVE_SETTINGS", "Aufgrund eines Fehlers konnten die benutzerdefinierten Einstellungen nicht gespeichert werden.\nVergewissern Sie sich, dass eine Datei mit dem Namen 'settings' im Hauptverzeichnis existiert.\nWenn das der Fall ist, konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_VALIDATION_FAILED", "Bei der Validierung des Drehbuchs ist ein Fehler aufgetreten.\nBitte konsultieren Sie das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			{"ERR_XML_SCHEMA_FILE", "Aufgrund eines Fehlers konnte die XML-Schemadatei nicht geladen werden.\nDies kann beim Laden von Drehb�chern zu unvorhergesehenen Validierungsfehlern f�hren.\nF�r weitere Informationen konsultieren Sie bitte das Fehlerprotokoll und wenden Sie sich an den Entwickler."},
			
			{"WRN_OVERWRITE", "Ein Drehbuch mit diesem Namen existiert bereits.\nSoll es �berschrieben werden?"},
			{"WRN_UNSAVED_CHANGES", "Nicht gespeicherte �nderungen gehen verloren.\nSpeichern?"},
			{"WRN_ARE_YOU_SURE", "Sind Sie sicher?"},
			{"WRN_LOCATION_EXISTS", "Ein Schauplatz mit diesem Namen existiert bereits."},
			{"WRN_CHARACTER_EXISTS", "Ein Charakter mit diesem Namen existiert bereits."},
			
			{"BTN_ADD", "Hinzuf�gen"},
			{"BTN_ADD_ACTION", "Handlung"},
			{"BTN_ADD_DIALOGUE", "Dialog"},
			{"BTN_APPLY", "Anwenden"},
			{"BTN_EDIT", "Bearbeiten"},
			{"BTN_OK", "OK"},
			{"BTN_REMOVE", "Entfernen"},
			
			{"PNL_PROPERTIES", "Eigenschaften"},
			{"PNL_LOCATION", "Schauplatz"},
			{"PNL_PLACE", "Ort"},
			{"PNL_TIME", "Zeit"},
			
			{"TAB_GENERAL", "Allgemein"},
			{"TAB_SCENES", "Szenen"},
			{"TAB_LOCATIONS", "Schaupl�tze"},
			{"TAB_CHARACTERS", "Charaktere"},
			
			{"DFT_CHARACTER", "Neuer Charakter"},
			{"DFT_LOCATION", "Neuer Schauplatz"},
			{"DFT_TITLE", "Neues Drehbuch"},
			
			{"TT_PROPERTIES_TITLE", "Geben Sie hier den Titel des Drehbuchs an. Sie k�nnen ihn nachtr�glich �ndern."},
			{"TT_PROPERTIES_AUTHOR", "Geben Sie hier den Namen des Drehbuchautors an. Sie k�nnen ihn nachtr�glich �ndern."},
			{"TT_PROPERTIES_FILENAME", "Geben Sie hier den Dateinamen an. Beachten Sie, dass dieser weder Punkte noch Schr�gstriche enthalten darf und auf '.xml' enden muss."},
			{"TT_PROPERTIES_LOCATION", "W�hlen Sie den Schauplatz f�r die Szene. Diese Angabe ist notwendig."},
			{"TT_PROPERTIES_MOD_LOCATION", "Spezifizieren Sie den Schauplatz f�r die Szene. Diese Angabe ist optional."},
			{"TT_PROPERTIES_PLACE", "W�hlen Sie den Standort der Kamera. Diese Angabe ist optional, wird aber empfohlen."},
			{"TT_PROPERTIES_TIME", "W�hlen Sie eine Tageszeit, zu der die Szene spielt. Diese Angabe ist optional, wird aber empfohlen."},
			{"TT_CREATE_SCENE_APPEND", "Erstellen Sie eine neue Szene und f�gen Sie sie dem Drehbuch hinzu."},
			{"TT_CREATE_SCENE_INSERT", "Erstellen Sie eine neue Szene und f�gen Sie sie vor der ausgew�hlten ein."},
			{"TT_CREATE_SCENE_NO_LOCATION", "Erstellen Sie zun�chst einen Schauplatz."},
			{"TT_EDIT_SCENE", "Bearbeiten Sie die ausgew�hlte Szene im Szeneneditor."},
			{"TT_EDIT_SCENE_NOTHING_SELECTED", "W�hlen Sie eine Szene aus, die Sie bearbeiten m�chten."},
			{"TT_MOVE_SCENE_UP", "Verschieben Sie die ausgew�hlte Szene nach vorn."},
			{"TT_MOVE_SCENE_UP_ALREADY_FIRST", "Das ist die erste Szene."},
			{"TT_MOVE_SCENE_DOWN", "Verschieben Sie die ausgew�hlte Szene nach hinten."},
			{"TT_MOVE_SCENE_DOWN_ALREADY_LAST", "Das ist die letzte Szene."},
			{"TT_MOVE_SCENE_NOTHING_SELECTED", "W�hlen Sie eine Szene aus, die Sie verschieben m�chten."},
			{"TT_REMOVE_SCENE", "Entfernen Sie die ausgew�hlte Szene aus dem Drehbuch."},
			{"TT_REMOVE_SCENE_NOTHING_SELECTED", "W�hlen Sie eine Szene aus, die Sie entfernen m�chten."},
			{"TT_ADD_LOCATION", "Erstellen Sie einen neuen Schauplatz."},
			{"TT_REMOVE_LOCATION", "Entfernen Sie den ausgew�hlten Schauplatz."},
			{"TT_REMOVE_LOCATION_USED_FOR_SCENE", "Der ausgew�hlte Schauplatz kann nicht entfernt werden, da er in einer Szene verwendet wird."},
			{"TT_REMOVE_LOCATION_NOTHING_SELECTED", "W�hlen Sie einen Schauplatz aus, den Sie entfernen m�chten."},
			{"TT_ADD_CHARACTER", "Erstellen Sie einen neuen Charakter."},
			{"TT_REMOVE_CHARACTER", "Entfernen Sie den ausgew�hlten Charakter."},
			{"TT_REMOVE_CHARACTER_USED_FOR_DIALOGUE", "Der ausgew�hlte Charakter kann nicht entfernt werden - er spricht einen Dialog in Szene "},
			{"TT_REMOVE_CHARACTER_NOTHING_SELECTED", "W�hlen Sie einen Charakter aus, den Sie entfernen m�chten."},
			{"TT_CREATE_ACTION_APPEND", "Erstellen Sie eine neue Handlung und f�gen Sie sie der Szene hinzu."},
			{"TT_CREATE_ACTION_INSERT", "Erstellen Sie eine neue Handlung und f�gen Sie sie vor dem ausgew�hlten Element ein."},
			{"TT_CREATE_DIALOGUE_APPEND", "Erstellen Sie einen neuen Dialog und f�gen Sie ihn der Szene hinzu."},
			{"TT_CREATE_DIALOGUE_INSERT", "Erstellen Sie einen neuen Dialog und f�gen Sie ihn vor dem ausgew�hlten Element ein."},
			{"TT_CREATE_DIALOGUE_NO_CHARACTER", "Erstellen Sie zun�chst einen Charakter."},
			{"TT_EDIT_ACTION", "Bearbeiten Sie die ausgew�hlte Handlung."},
			{"TT_EDIT_DIALOGUE", "Bearbeiten Sie den ausgew�hlten Dialog."},
			{"TT_EDIT_SCENE_ELEMENT_NOTHING_SELECTED", "W�hlen Sie ein Szenenelement aus, das Sie bearbeiten m�chten."},
			{"TT_MOVE_SCENE_ELEMENT_UP", "Verschieben Sie das ausgew�hlte Szenenelement nach vorn."},
			{"TT_MOVE_SCENE_ELEMENT_UP_ALREADY_FIRST", "Das ist das erste Szenenelement."},
			{"TT_MOVE_SCENE_ELEMENT_DOWN", "Verschieben Sie das ausgew�hlte Szenenelement nach hinten."},
			{"TT_MOVE_SCENE_ELEMENT_DOWN_ALREADY_LAST", "Das ist das letzte Szenenelement."},
			{"TT_MOVE_SCENE_ELEMENT_NOTHING_SELECTED", "W�hlen Sie ein Szenenelement aus, das Sie verschieben m�chten."},
			{"TT_REMOVE_SCENE_ELEMENT", "Entfernen Sie das ausgew�hlte Element aus der Szene."},
			{"TT_REMOVE_SCENE_ELEMENT_NOTHING_SELECTED", "W�hlen Sie ein Szenenelement aus, das Sie entfernen m�chten."},
		
			{"UPDATE_AVAILABLE", "Ein neues Update ist verf�gbar. Soll es installiert werden?"}
		};
	}
}