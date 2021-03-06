package alexiil.mods.load.baked.func.stack;

import java.util.Deque;

import alexiil.mods.load.baked.func.BakedFunction;
import alexiil.mods.load.baked.func.FunctionException;
import alexiil.mods.load.render.RenderingStatus;

public class BakedStackFunctionCaller extends BakedStackFunction {
    private final BakedFunction<?> func;

    public BakedStackFunctionCaller(BakedFunction<?> func) {
        this.func = func;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void doOperation(Deque stack, RenderingStatus status) throws StackFunctionException {
        try {
            int args = func.numArgs();
            Object[] objects = new Object[args];
            int i = 0;
            for (Object o : stack) {
                if (i == args)
                    break;
                objects[i] = o;
                i++;
            }
            stack.push(func.call(status, objects));
        }
        catch (FunctionException fe) {
            StackFunctionException sfe =
                new StackFunctionException("The function inside failed because {\n  " + fe.getMessage().replace("\n", "\n  ") + "}");
            sfe.initCause(fe);
            throw sfe;
        }
    }

    @Override
    public String toString() {
        return "Caller [] -> [(Any)]";
    }
}
