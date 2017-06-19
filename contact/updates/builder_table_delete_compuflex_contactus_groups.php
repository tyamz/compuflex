<?php namespace Compuflex\Contact\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableDeleteCompuflexContactusGroups extends Migration
{
    public function up()
    {
        Schema::dropIfExists('compuflex_contactus_groups');
    }
    
    public function down()
    {
        Schema::create('compuflex_contactus_groups', function($table)
        {
            $table->engine = 'InnoDB';
            $table->increments('id');
            $table->string('name');
            $table->text('permissions')->nullable();
            $table->timestamp('created_at')->nullable();
            $table->timestamp('updated_at')->nullable();
            $table->string('code')->nullable();
            $table->text('description')->nullable();
            $table->boolean('is_new_user_default')->default(0);
        });
    }
}
