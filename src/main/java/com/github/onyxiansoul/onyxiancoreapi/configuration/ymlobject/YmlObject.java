package com.github.onyxiansoul.onyxiancoreapi.configuration.ymlobject;
import com.github.onyxiansoul.onyxiancoreapi.actionable_system.ImpossibleActionException;
import com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class YmlObject{
  
  /**Gets the key for which this YmlObject is a value*/
  public abstract String getKey();
  
  /** * Gets the value of a field, using its fieldName as its objectType and no reference containers.Throws an exception if it the value is invalid or wasn't found
   * @param <T> The type of the field.
   * @param fieldName: The name of the field.
   * @throws IllegalArgumentException if the field was listed, but is invalid
   * @throws NullPointerException if the field isn't listed.
   * @return the value of the field
   * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException*/
  public abstract <T> T getRField(@NotNull String fieldName) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /** * Gets the value of a field, using its fieldName as its objectType.Throws an exception if it the value is invalid or wasn't found
   * @param <T> The type of the field.
   * @param fieldName: The name of the field.
   * @param referencesContainers
   * @throws IllegalArgumentException if the field was listed, but is invalid
   * @throws NullPointerException if the field isn't listed.
   * @return the value of the field
   * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException*/
  public abstract <T> T getRField(@NotNull String fieldName,@Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;

  /**Gets the value of a field, using its fieldName as its objectType and no reference containers, or returns a default value if the field is not available
   * @param <T> The type of the field.
   * @param fieldName The name of the field.
   * @param defaultValue The default value for the field
   * @return the value of the field or the default value if its not listed
   * @throws IllegalArgumentException If the field was listed, but is invalid
   * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException*/
  public abstract <T> T getRFieldOrDefault(@NotNull String fieldName, T defaultValue) throws IllegalArgumentException, UnexpectedConfigurationException;
  
  /**Gets the value of a field, using its fieldName as its objectType, or returns a default value if the field is not available
   * @param <T> The type of the field.
   * @param fieldName The name of the field.
   * @param defaultValue The default value for the field
   * @param referencesContainers
   * @return the value of the field or the default value if its not listed
   * @throws IllegalArgumentException If the field was listed, but is invalid
   * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException*/
  public abstract <T> T getRFieldOrDefault(@NotNull String fieldName, T defaultValue, @Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, UnexpectedConfigurationException;

  /**Gets the value of a field, using its fieldName as its objectType, or returns the value of a registered variable, if the field is not available
   * @param <T> The type of the field.
   * @param fieldName The name of the field.
   * @param variableName The name the variable was registered by.
   * @param referencesContainers
   * @return the value of the field or the default value if its not listed
   * @throws IllegalArgumentException If the field was listed, but is invalid
   * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException*/
  public abstract <T> T getRFieldOrDefaultToVariable(@NotNull String fieldName,@NotNull String variableName, @Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, UnexpectedConfigurationException;
  
  /**Gets the result of enacting the value of a field with null runtime cirumstances, or defaults to the default value if its null.*/
  public abstract <T> T getRFieldWrappedValueOrDefault(@NotNull String fieldName, T defaultValue) throws ImpossibleActionException, UnexpectedConfigurationException;

  /**Gets the result of enacting the value of a field with null runtime circumstances*/
  public abstract <T> T getRFieldWrappedValue(@NotNull String fieldName) throws ImpossibleActionException, UnexpectedConfigurationException;
  
  /**Gets a sub YmlObject inside of this one
   * @param <T> The type of the field
   * @param fieldName The key of the field
   * @param referencesContainers
   * @return The YmlObject located inside of this one.
  */
  public abstract YmlObject getYmlObject(@NotNull String fieldName, @Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /**Gets a list inside a field of this YmlObject
  * @param <T> The type of the elements held in the list.
  * @param fieldName The config name of the list. 
  * @param objectTypeReferenceSection 
  * @param simpleObjectType The name of the type of variable inside the list (as it was registered in the API)
  * @return A list of the actionables produced from every value inside the config list, in the order they were on the config list.
  * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException
  */
  public abstract <T> List<T> getListObjects(@NotNull String fieldName, @NotNull String simpleObjectType, @Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /**Gets a list inside a field of this YmlObject or the object definition
  * @param <T> The type of the elements held in the list.
  * @param fieldName The config name of the list. 
  * @param objectTypeReferenceSection 
  * @return A list of the actionables produced from every value inside the config list, in the order they were on the config list.
  * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException
  */
  public abstract <T> List<T> getListFieldOrDefinitionObjects(@NotNull String fieldName, @NotNull String objectType, @Nullable List<YmlObject> objectTypeReferenceSection) throws UnexpectedConfigurationException;
  
  /**Gets a collection of all the objects of objectType created from the objects inside the section. It respects the order inside the config and cannot have duplicate entries.
  * @param <T> The type of the elements held in the list.
  * @param fieldName The config name of the list. 
  * @param objectType The name of the type of variable inside the list (as it was registered in the API)
  * @return A list of the actionables produced from every value inside the config list, in the order they were on the config list.
  */
  public abstract <T> Collection<T> getSectionObjects(@NotNull String fieldName,@NotNull String objectType, @Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /**Gets a map of all the keys located inside the section & the object of objectType created from the section. It respects the order inside the config, and cannot have two objects of the same key.
  * @param <T> The type of the elements held in the list.
  * @param fieldName The config name of the list. 
  * @param objectType The name of the type of variable inside the list (as it was registered in the API)
  * @return A list of the actionables produced from every value inside the config list, in the order they were on the config list.
  */
  public abstract <T> Map<String,T> getSectionObjectsMap(@NotNull String fieldName, @NotNull String objectType, @Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /**Gets a collection of all the objects of objectType created from the objects in the definition of this one. It respects the order inside the config and cannot have duplicate entries.
  * @param <T> The type of the elements held in the list.
  * @param objectType The name of the type of variable inside the list (as it was registered in the API)
  * @return A list of the actionables produced from every value inside the config list, in the order they were on the config list.
  */
  public abstract <T> Collection<T> getSectionObjects(@NotNull String objectType, @Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /**Gets a map of all the keys located inside the section & the object of objectType created from this section. It respects the order inside the config, and cannot have two objects of the same key.
  * @param <T> The type of the elements held in the list.
  * @param objectType The name of the type of variable inside the list (as it was registered in the API)
  * @return A list of the actionables produced from every value inside the config list, in the order they were on the config list.
  */
  public abstract <T> Map<String,T> getSectionObjectsMap(@NotNull String objectType, @Nullable List<YmlObject> referencesContainers) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /**Gets all the yml object containing which can be used as a reference by this yml object.
   Its usage is discouraged, since it shouldn't be required unless you are implementing YmlObject, which is unnecessary, since the OnyxianCore already does that.*/
  public abstract List<YmlObject> getReferencesContainers();
  
  /**Gets all the value of every field of the yml object, included ones inherited from defaulting from other objects.
   Its usage is discouraged since most operations can be performed in an easier and less error prone way using other method provided.*/
  public abstract Map<String,Object> getFullRawValues();
  
  /**Gets all the value of every field of the yml object, excluiding ones inherited from defaulting from other objects.
   Its usage is discouraged since most operations can be performed in an easier and less error prone way using other method provided.*/
  public abstract Map<String,Object> getObjectRawValues();
  
  /**Gets the raw value of a field.
   Discouraged since the actionable system should be used and therefore getRField (or it's variants) should be used (unless implementing yml object) (which is also unnecessary & discouraged)*/
  public abstract Object getFieldRawValue(@NotNull String key) throws UnexpectedConfigurationException;

  /**Gets the raw value of a field, following references for example: (copy:nameOfYmlObjectToImitate)
   Discouraged since the actionable system should be used and therefore getRField (or it's variants) should be used (unless implementing yml object) (which is also unnecessary & discouraged)*/
  public abstract Object getFieldRawValueFollowingReferences(@NotNull String key) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /**Returns the value of a yml object's field using its fieldName as its objectType. If it doesn't exist, returns the definition of the yml object.
   * Used mainly on object types that can have a single parameter.
   * for example getFieldOrDefinition("color") in any of this two configs would yield red:
   * MyObject:
   *   color: red
   *   stripy: true
   * 
   * MyObject: red
   * 
   * @param <T> The type of the field.
   * @param fieldName: The name of the field.
   * @throws IllegalArgumentException if the field was listed, but is invalid
   * @throws NullPointerException if the field isn't listed.
   * @return the value of the field*/
  public abstract <T> T getRFieldOrDefinition(@NotNull String fieldName) throws IllegalArgumentException, NullPointerException,  UnexpectedConfigurationException;
  
  /**Gets the raw value of the field, following references if necessairy. Meant to be used by direct wrappers (ie 'text', 'boolean', etc, which don't have any fields other than the definition)*/
  public abstract Object getDefinition() throws IllegalArgumentException, NullPointerException,  UnexpectedConfigurationException;
  
  /** Makes an element of a registered type from this yml object's values. This is NOT a replacement for properly registering a type.
   *  Instead, its meant to be a a way for a registered type to gain parts from different registered type without requiring a substring inside.*/
  public abstract <T> T getDefinitonAs(String registeredType) throws UnexpectedConfigurationException;
  
  /**Gets the value this object was created to wrap.*/
  public abstract Object getRawValue() throws IllegalArgumentException, NullPointerException,  UnexpectedConfigurationException;
  
  /**Sets the defaultFromFieldValue section for this object & completes the YmlObject values using its values for default_from & copy fields
   * This is used by the core but its unlikely that you should need it as an external plugin.
   * @param referencesContainers The section containing ymlobjects used to produced elements of the same type as the object represented by this YmlObject
   * @throws UnexpectedConfigurationException if there was a problem while using the new defaultFromFieldValue section to modify the values of the YmlObject.
   */
  public abstract void setReferenceSection(@Nullable List<YmlObject> referencesContainers) throws UnexpectedConfigurationException;
  
}






  /** * Returns the value of a yml object's field of objectType.If it doesn't exist, returns the definition of the yml object. for example getFieldOrDefinition("color") in any of this two configs would yield red:
 MyObject:
   color: red
   stripy: true
 
 MyObject: red
   * 
   * @param <T> The type of the field.
   * @param fieldName The name of the field.
   * @param objectType The name of the type of variable to use to interpret this field
   * @throws IllegalArgumentException if the field was listed, but is invalid
   * @throws NullPointerException if the field isn't listed.
   * @return the value of the field*/
  //public abstract <T> T getFieldOfRTypeOrDefinition(String fieldName, String objectType) throws IllegalArgumentException;





  //Removed because its unnecesairy since field sinonyms can be registered and might generate confusion.
  //----------------------------------------------------------------------------------------------------


  /**Gets the value of a field of objectType(that has been registered on the API) or throws an exception if it is invalid or wasn't found
   * @param <T> The type of the field.
   * @param fieldName The name of the field.
   * @param objectType The name of the type of variable to use to interpret this field (as it was registered in the API)
   * @param referencesContainers
   * @throws IllegalArgumentException if the field was listed, but is invalid
   * @throws NullPointerException if the field isn't listed.
   * @return the value of the field
   * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException*/
  //public abstract <T> T getFieldOfRType(String fieldName, String objectType, List<YmlObject> referencesContainers) throws IllegalArgumentException, NullPointerException, UnexpectedConfigurationException;
  
  /**Gets the value of a field of objectType(that has been registered on the API) or returns a default value if its not available
   * @param <T> The type of the field.
   * @param fieldName The name of the field.
   * @param objectType The name of the type of variable (as it was registered in the API)
   * @param referencesContainers
   * @param defaultValue The default value for the field
   * @return the value of the field or the default value if its not listed
   * @throws IllegalArgumentException If the field was listed, but is invalid
   * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException*/
  //public abstract <T> T getFieldOfRTypeOrDefault(String fieldName, String objectType, List<YmlObject> referencesContainers, T defaultValue) throws IllegalArgumentException, UnexpectedConfigurationException;

  /**Gets the value of a field of objectType, or returns the value of a registered variable, if the field is not available
   * @param <T> The type of the field.
   * @param fieldName The name of the field.
   * @param objectType The name of the type of variable to use to interpret this field (as it was registered in the API)
   * @param referencesContainers
   * @param variableName The name the variable was registered by.
   * @return the value of the field or the default value if its not listed
   * @throws IllegalArgumentException If the field was listed, but is invalid
   * @throws com.github.onyxiansoul.onyxiancoreapi.configuration.exceptions.UnexpectedConfigurationException*/
  //public abstract <T> T getFieldOfRTypeOrDefaultToVariable(String fieldName, String objectType, List<YmlObject> referencesContainers, String variableName) throws IllegalArgumentException, UnexpectedConfigurationException;
