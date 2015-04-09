package jsonTest;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Test {

  public static void main(String[] args) throws JSONException {
  String json = "{\"name\":\"reiz\"}";
  JSONObject jsonObj = new JSONObject(json);
  String name = jsonObj.getString("name");

  jsonObj.put("initial", name.substring(0, 1).toUpperCase());

  String[] likes = new String[] { "JavaScript", "Skiing", "Apple Pie" };
  jsonObj.put("likes", likes);

  Map<String, String> ingredients = new HashMap<String, String>();
  ingredients.put("apples", "Æ»¹û");
  ingredients.put("sugar", "1kg");
  ingredients.put("pastry", "2.4kg");
  ingredients.put("bestEaten", "outdoors");
  jsonObj.put("ingredients", ingredients);
  System.out.println(jsonObj);

  System.out.println(jsonObj);
  }
}