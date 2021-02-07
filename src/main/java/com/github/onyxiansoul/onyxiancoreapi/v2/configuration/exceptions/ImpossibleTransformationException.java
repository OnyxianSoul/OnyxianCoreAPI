package com.github.onyxiansoul.onyxiancoreapi.v2.configuration.exceptions;

/**An exception thrown when its impossible to craft an object of a certain type from an 'Object'.
 */
public class ImpossibleTransformationException extends Exception {
    private static String craftMessage(String className, Object baseObject, String additionalMessage){
        return "Attempted to convert "+ baseObject.toString() + " To " + className +" But it was impossible." + additionalMessage;
    }
    
    public ImpossibleTransformationException(String className, Object baseObject){
        super(craftMessage(className,baseObject,""));
    }
    
    public ImpossibleTransformationException(String className, Object baseObject, String additionalMessage){
        super(craftMessage(className,baseObject,""));
    }
    
    public ImpossibleTransformationException(String className, Object baseObject, Exception e){
        super(craftMessage(className,baseObject,""), e);
    }
    
    public ImpossibleTransformationException(String className, Object baseObject, String additionalMessage, Exception e){
        super(craftMessage(className,baseObject,additionalMessage), e);
    }

  ImpossibleTransformationException(String string) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
        
}
