package crazylemon.cyberlimb_fix;

import uk.co.mysterymayhem.gravitymod.asm.Transformer;
import uk.co.mysterymayhem.gravitymod.asm.util.obfuscation.names.MethodName;
import uk.co.mysterymayhem.gravitymod.asm.util.patching.ClassPatcher;
import uk.co.mysterymayhem.gravitymod.asm.util.patching.MethodPatcher;

public class PatchRenderPlayerCyberware extends ClassPatcher {
	public PatchRenderPlayerCyberware() {
		super("flaxbeard.cyberware.client.render.RenderPlayerCyberware", MethodPatcher.create(
				new MethodName("doRenderELB", "doRenderELB")::is,
				(methodNode -> Transformer.patchMethodUsingAbsoluteRotations(methodNode, Transformer.ALL_GET_ROTATION_VARS))));
	}
}
