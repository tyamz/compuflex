<?php namespace Compuflex\Contact\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableUpdateCompuflexContactMessages extends Migration
{
    public function up()
    {
        Schema::table('compuflex_contact_messages', function($table)
        {
            $table->text('subject')->nullable();
        });
    }
    
    public function down()
    {
        Schema::table('compuflex_contact_messages', function($table)
        {
            $table->dropColumn('subject');
        });
    }
}
