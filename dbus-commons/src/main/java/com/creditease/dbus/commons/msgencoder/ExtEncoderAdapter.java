/*-
 * <<
 * DBus
 * ==
 * Copyright (C) 2016 - 2017 Bridata
 * ==
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * >>
 */

package com.creditease.dbus.commons.msgencoder;

import com.creditease.dbus.commons.DbusMessage;
import com.creditease.dbus.encoders.EncoderConf;
import com.creditease.dbus.encoders.ExtEncodeStrategy;

/**
 * Created by zhangyf on 17/6/1.
 */
public class ExtEncoderAdapter implements EncodeStrategy {
    private ExtEncodeStrategy extEncoder;

    public ExtEncoderAdapter(ExtEncodeStrategy strategy) {
        extEncoder = strategy;
    }

    @Override
    public Object encode(DbusMessage.Field field, Object value, EncodeColumn col) {
        EncoderConf conf = new EncoderConf();
        conf.setDesc(col.getDesc());
        conf.setEncodeParam(col.getEncodeParam());
        conf.setEncodeType(col.getEncodeType());
        conf.setFieldName(col.getFieldName());
        conf.setLength(col.getLength());
        conf.setTruncate(col.isTruncate());
        try {
            return extEncoder.encode(conf, value);
        }catch (Exception e){
            return e.toString();
        }
    }
}
