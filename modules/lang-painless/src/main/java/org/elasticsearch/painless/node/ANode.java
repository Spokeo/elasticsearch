/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.painless.node;

import org.elasticsearch.painless.MethodWriter;
import org.objectweb.asm.Label;

/**
 * The superclass for all other nodes.
 */
public abstract class ANode {

    /**
     * The line number in the original source used for debug messages.
     */
    final int line;

    /**
     * The location in the original source to be printed in error messages.
     */
    final String location;

    ANode(final int line, final String location) {
        this.line = line;
        this.location = location;
    }

    public String error(final String message) {
        return "Error " + location  + ": " + message;
    }
    
    /** 
     * Writes line number information
     * <p>
     * Currently we emit line number data for for leaf S-nodes
     */
    void writeDebugInfo(MethodWriter adapter) {
        Label label = new Label();
        adapter.visitLabel(label);
        adapter.visitLineNumber(line, label);
    }
}
