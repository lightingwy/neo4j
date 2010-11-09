package org.neo4j.kernel;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.event.KernelEventHandler;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.neo4j.graphdb.index.IndexManager;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

/**
 * A database meant to be used in unit tests. It will always be empty on start.
 */
public class ImpermanentGraphDatabase extends AbstractGraphDatabase
{
    private EmbeddedGraphDatabase inner;
    private String storeDir;

    public ImpermanentGraphDatabase( String storeDir )
    {
        this.storeDir = storeDir;
        deleteRecursively( new File(storeDir) );
        inner = new EmbeddedGraphDatabase( storeDir );
    }

    private static void deleteRecursively( File file )
    {
        if ( !file.exists() )
        {
            return;
        }

        if ( file.isDirectory() )
        {
            for ( File child : file.listFiles() )
            {
                deleteRecursively( child );
            }
        }
        else
        {
            if (!file.delete())
            {
                throw new RuntimeException( "Couldn't empty database." );
            }
        }
    }

    public Node createNode()
    {
        return inner.createNode();
    }

    public Node getNodeById( long id )
    {
        return inner.getNodeById( id );
    }

    public Relationship getRelationshipById( long id )
    {
        return inner.getRelationshipById( id );
    }

    public Node getReferenceNode()
    {
        return inner.getReferenceNode();
    }

    public Iterable<Node> getAllNodes()
    {
        return inner.getAllNodes();
    }

    public Iterable<RelationshipType> getRelationshipTypes()
    {
        return inner.getRelationshipTypes();
    }

    public void shutdown()
    {
        inner.shutdown();
        deleteRecursively( new File(storeDir) );
    }

    public boolean enableRemoteShell()
    {
        return inner.enableRemoteShell();
    }

    public boolean enableRemoteShell(
            Map<String, Serializable> initialProperties )
    {
        return inner.enableRemoteShell( initialProperties );
    }

    public Transaction beginTx()
    {
        return inner.beginTx();
    }

    public <T> TransactionEventHandler<T> registerTransactionEventHandler(
            TransactionEventHandler<T> handler )
    {
        return inner.registerTransactionEventHandler( handler );
    }

    public <T> TransactionEventHandler<T> unregisterTransactionEventHandler(
            TransactionEventHandler<T> handler )
    {
        return inner.unregisterTransactionEventHandler( handler );
    }

    public KernelEventHandler registerKernelEventHandler(
            KernelEventHandler handler )
    {
        return inner.registerKernelEventHandler( handler );
    }

    public KernelEventHandler unregisterKernelEventHandler(
            KernelEventHandler handler )
    {
        return inner.unregisterKernelEventHandler( handler );
    }

    public IndexManager index()
    {
        return inner.index();
    }

    @Override
    public String getStoreDir()
    {
        return inner.getStoreDir();
    }

    @Override
    public Config getConfig()
    {
        return inner.getConfig();
    }

    @Override
    public <T> T getManagementBean( Class<T> type )
    {
        return inner.getManagementBean( type );
    }

    @Override
    public boolean isReadOnly()
    {
        return inner.isReadOnly();
    }
}
