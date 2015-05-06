package alexiil.mods.load.json;

import java.util.Map;

import alexiil.mods.load.baked.BakedRenderingPart;
import alexiil.mods.load.baked.factory.BakedFactoryStatus;
import alexiil.mods.load.baked.func.FunctionBaker;
import alexiil.mods.load.baked.func.IBakedFunction;

public class JsonFactoryStatus extends JsonFactory {
    public JsonFactoryStatus(String shouldDestroy, JsonRenderingPart toCreate) {
        // None of the arguments matter
        super("false", shouldDestroy, toCreate);
    }

    public JsonFactoryStatus() {
        // None of the arguments matter
        super("false", "false", null);
    }

    @Override
    public BakedFactoryStatus actuallyBake(Map<String, IBakedFunction<?>> functions) {
        IBakedFunction<Boolean> shouldDestroy = FunctionBaker.bakeFunctionBoolean(this.shouldDestroy, functions);
        BakedRenderingPart component = toCreate.bake(functions);
        return new BakedFactoryStatus(shouldDestroy, component);
    }

    /** This is overridden just so that the correct type is returned, with no casting needed. Note that the children will
     * have to consolidate down to this class, otherwise it won't work properly. */
    @Override
    protected JsonFactoryStatus actuallyConsolidate() {
        return this;
    }
}