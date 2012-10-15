/*
 * Copyright 2011 VMWare.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo.vmware.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.vmware.commands.ICommandProcessor;

/**
 * Gemfire caching client startup class. This also spins up a spring configured command console.
 * Run this after the locator and Server
 * 
 * You will need to run this with over 1GB of heap, maybe -Xmx1500m if you load the demo data *
 */
public class Client {

    public static void main(String[] args) throws Exception {
        String resource[] = { "spring-cache-client-core.xml", "spring-cache-client-region-only.xml",
                "spring-cache-templates.xml", "spring-command-processor.xml", "spring-datasync.xml", };
        ClassPathXmlApplicationContext mainContext = new ClassPathXmlApplicationContext(resource, false);
        mainContext.setValidating(true);
        mainContext.refresh();
        ICommandProcessor cp = mainContext.getBean(ICommandProcessor.DEFAULT_PROCESSOR_NAME, ICommandProcessor.class);
        cp.run(mainContext);
    }

}
