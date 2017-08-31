package crazylemon.cyberlimb_fix;

import java.util.HashMap;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import net.minecraft.launchwrapper.IClassTransformer;
import uk.co.mysterymayhem.gravitymod.asm.util.patching.ClassPatcher;
import uk.co.mysterymayhem.gravitymod.asm.util.patching.PatchFailedException;

/* 
 * Most of this is aped from MysterEm's Transformer code, since it piggy-backs off of it
 */
public class CyberlimbFixClassTransformer implements IClassTransformer {
    // Maps classes to their patch methods
    private static final HashMap<String, ClassPatcher> classNameToMethodMap = new HashMap<>();
    private static final Logger logger = LogManager.getLogger("CyberlimbFix-Core", StringFormatterMessageFactory.INSTANCE);


    static {
    	addClassPatch(new PatchRenderPlayerCyberware());
    }

    private static void addClassPatch(ClassPatcher patcher) {
        ClassPatcher oldVal = classNameToMethodMap.put(patcher.getClassName(), patcher);
        if (oldVal != null) {
            die("Duplicate class patch for " + oldVal.getClassName());
        }
    }

    /**
     * Default logging method.
     *
     * @param string  Formattable string a la String::format
     * @param objects Objects to be passed for formatting in log message
     */
    public static void log(String string, Object... objects) {
        logger.info(string, objects);
    }

    /**
     * Default 'crash Minecraft' method.
     *
     * @param string Reason why we are forcing Minecraft to crash
     */
    public static void die(String string) throws PatchFailedException {
        throw new PatchFailedException("[CyberlimbFix] " + string);
    }

    /**
     * Core transformer method. Recieves classes by name and their bytes and processes them if necessary.
     *
     * @param name                 class name prior to deobfuscation (I think)
     * @param transformedName      runtime deobfuscated class name
     * @param basicClass           the bytes that make up the class sent to the transformer
     * @return the bytes of the now processed class
     */
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if(basicClass == null) {
			return null;
		}
		
		Function<byte[], byte[]> function = classNameToMethodMap.get(transformedName);
		
		if(function == null) {
			return basicClass;
		}
		else {
			classNameToMethodMap.remove(transformedName);
			log("Patching class %s", transformedName);
			byte[] toReturn = function.apply(basicClass);
			log("Patched class  %s", transformedName);
			return toReturn;
		}
	}

}
