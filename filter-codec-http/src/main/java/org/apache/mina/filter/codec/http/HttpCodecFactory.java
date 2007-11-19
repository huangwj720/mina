/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.filter.codec.http;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * TODO HttpProtocolCodecFactory.
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev$, $Date$
 */
public class HttpCodecFactory implements ProtocolCodecFactory {

    private final ProtocolEncoder requestEncoder = new HttpRequestEncoder();
    private final ProtocolEncoder responseEncoder = new HttpResponseEncoder();

    public HttpCodecFactory() {
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        if (session.getService() instanceof IoAcceptor) {
            return responseEncoder;
        } else {
            return requestEncoder;
        }
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        if (session.getService() instanceof IoAcceptor) {
            return new HttpRequestDecoder();
        } else {
            return new HttpResponseDecoder();
        }
    }
}
