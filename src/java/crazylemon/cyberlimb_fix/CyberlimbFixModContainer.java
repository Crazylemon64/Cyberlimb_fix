package crazylemon.cyberlimb_fix;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class CyberlimbFixModContainer extends DummyModContainer {
	public CyberlimbFixModContainer() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "cyberlimb_fix";
		meta.name = "Cyberlimb Fix";
		meta.description = "Fixes Cyberlimb rendering when using Cyberware and UDAA together.";
		meta.version = "1.10.2-1.0";
		meta.authorList = Arrays.asList("Crazylemon");
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
}
