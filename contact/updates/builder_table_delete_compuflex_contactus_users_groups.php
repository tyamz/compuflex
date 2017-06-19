<?php namespace Compuflex\Contact\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableDeleteCompuflexContactusUsersGroups extends Migration
{
    public function up()
    {
        Schema::dropIfExists('compuflex_contactus_users_groups');
    }
    
    public function down()
    {
        Schema::create('compuflex_contactus_users_groups', function($table)
        {
            $table->engine = 'InnoDB';
            $table->integer('user_id');
            $table->integer('group_id');
            $table->primary(['user_id','group_id']);
        });
    }
}
