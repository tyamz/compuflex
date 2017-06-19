<?php namespace Compuflex\Contact\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableUpdateCompuflexContactMessages2 extends Migration
{
    public function up()
    {
        Schema::table('compuflex_contact_messages', function($table)
        {
            $table->dateTime('update_at')->nullable();
            $table->timestamp('deleted_at')->nullable();
            $table->renameColumn('date_sent', 'created_at');
        });
    }
    
    public function down()
    {
        Schema::table('compuflex_contact_messages', function($table)
        {
            $table->dropColumn('update_at');
            $table->dropColumn('deleted_at');
            $table->renameColumn('created_at', 'date_sent');
        });
    }
}
