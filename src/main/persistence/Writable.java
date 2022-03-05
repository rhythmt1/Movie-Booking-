package persistence;

import org.json.JSONObject;

public interface Writable {
    //This class is modelled after JsonSerializationDemo project.
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

