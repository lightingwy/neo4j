/*
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.api.impl.schema.sampler;

import org.neo4j.helpers.TaskControl;
import org.neo4j.kernel.api.exceptions.index.IndexNotFoundKernelException;
import org.neo4j.kernel.api.impl.schema.reader.PartitionedIndexReader;
import org.neo4j.kernel.impl.api.index.sampling.IndexSamplingConfig;
import org.neo4j.register.Register;

public class NonUniquePartitionedIndexSampler extends LuceneIndexSampler
{
    private final PartitionedIndexReader indexReader;
    private final IndexSamplingConfig indexSamplingConfig;

    public NonUniquePartitionedIndexSampler( PartitionedIndexReader indexReader, TaskControl taskControl,
            IndexSamplingConfig indexSamplingConfig )
    {
        super( taskControl );
        this.indexReader = indexReader;
        this.indexSamplingConfig = indexSamplingConfig;
    }

    @Override
    protected long performSampling( Register.DoubleLong.Out result ) throws IndexNotFoundKernelException
    {
        return -1;
    }
}