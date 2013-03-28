package rozprochy.common.hermes.impl;

import java.lang.annotation.Annotation;

import rozprochy.common.hermes.annotations.Description;
import rozprochy.common.hermes.annotations.Optional;
import rozprochy.common.hermes.annotations.Param;
import rozprochy.common.hermes.annotations.ParseWith;

public class ParameterAnalyzer {
    
    private ParameterInfo info = new ParameterInfo();
    
    public ParameterAnalyzer(Class<?> type, Annotation[] annotations,
            int positon) {
        info.setParamClass(type);
        info.setPositon(positon);
        for (Annotation a : annotations) {
            processAnnotation(a);
        }
    }
    
    private void processAnnotation(Annotation a) {
        if (a instanceof Param) {
            Param param = (Param) a;
            info.addAllNames(param.names());
        } else if (a instanceof Optional) {
            //Optional opt = (Optional) a;
            //String def = opt.defaultValue();
            // TODO: Parse the value here! Or don't and change some other code.
        } else if (a instanceof ParseWith) {
            ParseWith parser = (ParseWith) a;
            info.setParser(parser.value());
        } else if (a instanceof Description) {
            Description desc = (Description) a;
            info.setDescription(desc.value());
        }
    }

    public ParameterInfo get() {
        //fvrxdtgtry
        return info;
    }

}
