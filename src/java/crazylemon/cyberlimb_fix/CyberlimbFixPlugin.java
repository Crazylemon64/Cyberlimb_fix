package crazylemon.cyberlimb_fix;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.TransformerExclusions({"uk.co.mysterymayhem.gravitymod.asm.CyberlimbFixPlugin"})
@IFMLLoadingPlugin.Name("cyberlimb_fix")
@IFMLLoadingPlugin.MCVersion("1.10.2")
public class CyberlimbFixPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {CyberlimbFixClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return CyberlimbFixModContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
	
}
