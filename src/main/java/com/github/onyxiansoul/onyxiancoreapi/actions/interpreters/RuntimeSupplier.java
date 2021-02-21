package com.github.onyxiansoul.onyxiancoreapi.actions.interpreters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/** The base for any RuntimeSupplier aka any class that from an object is able to return useful data for at least one of the methods in this class.All Onyxian plugins that require obtaining data have their unique implementation of this class
 * Only onyxian plugins should extend this interface directly.
 * All other plugins should extend the implementation located on the api of one of the onyxianPlugins.
 * @param <T> The type of object it uses internally for supplying methods.
 */
public abstract class RuntimeSupplier<T> {
  private static final Map<Class<? extends RuntimeSupplier>, Map<String,AdditionalFieldGetter>> additionalSupplierFields = new HashMap<>();
  //TODO add plugin info; consider moving to index and locking behind the api.
  
  public static final void addFieldGetterToSupplier(Class<? extends RuntimeSupplier> supplierClass, String fieldName, AdditionalFieldGetter fieldGetter) throws IllegalArgumentException{
    Map<String, AdditionalFieldGetter> supplierAdditionalGettersMap = additionalSupplierFields.getOrDefault(supplierClass, new HashMap<String, AdditionalFieldGetter>());
    if(!supplierAdditionalGettersMap.containsKey(fieldName)){
      supplierAdditionalGettersMap.put(fieldName, fieldGetter);
      additionalSupplierFields.put(supplierClass, supplierAdditionalGettersMap);
    }
    else{
      throw new IllegalArgumentException("The supplier: "+ supplierClass.getSimpleName() + " Already has a getter for the additional field: "+ fieldName);
    }
  }  
  
  protected final T e;

  public RuntimeSupplier(@NotNull T runtimeObject){
    this.e = runtimeObject;
  }

  /**Get the name of the event
   * @param e = an event of this type
   * @return  The name of the event
   */
  @Nullable
  public String getEventName(){return null; }

  /**Get the handlers list for the event
   * @param e = an event of this type
   * @return  The handlers for the event.
   */
  @Nullable
  public HandlerList getHandlersList() { return null; }

  /**Get the class of the event
   * @param e = an event of this type
   * @return  The class of the event
   */
  @Nullable
  public Class<?> getEventClass(){ return null; }

  /**Check if the event has been cancelled (usually by another plugin)
   * @param e = an event of this type
   * @return  true if it has been cancelled, false if it hasn't (or isn't cancellable).
   */
  @NotNull
  public boolean isCancelled(){ return false; }

  /**Get the name of the data obtainer, it will be used as a unique ID when registering the data obtainer And as string signal, when reading the config file.
   * Please, DO NOT include EVENT, OBTAINER or DATA_OBTAINER in the name & separate worlds with underscores.
   * This will help mantain naming consistency, and make it easier for users configurating the plugin.
   * @return the Config String signal and Unique identifier for this data obtainer. */
  @NotNull
  public abstract String getDataObtainerName();

  /** Get the blocks affected by this event.
   * @param e = an event of this type
   * @return  The blocks affected by this event.Should always have a block. Can't be null or empty*/
  @Nullable
  public List<Block> getAffectedBlocks() {return null;}

  /** Get the Player who triggered the event
   * @param e = an event of this type
   * @return The player who triggered the event. Can be null if the event wasn't caused by a player.
   */
  @Nullable
  public Player getTriggerPlayer() { return null ; }

  /**Get the Explosion cause of this event
   * @param e = an event of this type
   * @return The Entity that generated the explosion. Will be null if it the event wasn't caused by an explosion or if the explosion wasn't caused by an entity.
   */
  @Nullable
  public EntityType getExplosionCause(){ return null; }

  /**Get the Exp dropped by the event
   * @param e = an event of this type
   * @return The Entity that generated the explosion. Will be null if it the event doesn't drop xp.
   */
  @Nullable
  public Integer getExpDropped(){ return null; }

  /**Check if the event causes the drop of items.
   * @param e = an event of this type
   * @return true if the event drops items, false if it doesnt
   */
  @NotNull
  public boolean isItemDropper(){ return false; }

  /**Gets the block face which was interacted with (in one way or another), when the event was triggered
   * @param e = an event of this type
   * @return the block face which was interacted with (in one way or another), when the event was triggered.
   */
  @Nullable
  public BlockFace getBlockFace() { return null; } 

  /**Check if the block is instabroken as a result of the event.
   * @param e = an event of this type
   * @return false if the event doesn't break the block instantly or doesn't break the block at all.
   */
  @NotNull
  public boolean isBlockInstaBroken() { return false; } 

  /**Get the list of blockStates which are involved on an event. 
   * Blocks represent the current status of a cube in a location of a world.
   * Like blocks, blockstates represent the material + blockData of a location,
   * but unlike blocks, they may represent a previous, current, future or possible cube for a location.
   * Thats why some events, which deal with changing status of a block, such as replacements will
   * in addition to blocks have blockStates involved in the event, representing either the past or future of a cube.
   * @param e = an event of this type
   * @return the list of blockStates which are involved on an event. Usually a single entry, representing the past or future of a cube.
  */
  @Nullable
  public List<BlockState> getInvolvedStates(){ return null; }

  /**Get the type of element this obtainer is using as its source.*/
  @NotNull
  public final Class getType(){
    return e.getClass();
  }

  /**Get the element this obtainer is using as its source*/
  @NotNull
  public final T getRaw(){
    return e;
  }

  @Nullable
  public Object getField(String fieldName){
    return additionalSupplierFields.getOrDefault(getClass(), new HashMap<>()).getOrDefault(fieldName,null);
  }
    
}