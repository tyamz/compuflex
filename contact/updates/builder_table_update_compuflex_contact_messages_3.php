<?php namespace Compuflex\Contact\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableUpdateCompuflexContactMessages3 extends Migration
{
    public function up()
    {
        Schema::table('compuflex_contact_messages', function($table)
        {
            $table->timestamp('updated_at')->nullable();
            $table->timestamp('deleted_at')->nullable();
        });
    }
    
    public function down()
    {
        Schema::table('compuflex_contact_messages', function($table)
        {
            $table->dropColumn('updated_at');
            $table->dropColumn('deleted_at');
        });
    }
}
