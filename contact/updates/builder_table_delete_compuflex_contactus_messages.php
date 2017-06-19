<?php namespace Compuflex\Contact\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableDeleteCompuflexContactusMessages extends Migration
{
    public function up()
    {
        Schema::dropIfExists('compuflex_contactus_messages');
    }
    
    public function down()
    {
        Schema::create('compuflex_contactus_messages', function($table)
        {
            $table->engine = 'InnoDB';
            $table->increments('id');
            $table->text('message');
            $table->dateTime('date');
            $table->text('sender_name');
            $table->text('sender_email');
            $table->integer('recipient_group');
            $table->timestamp('created_at')->nullable();
            $table->timestamp('updated_at')->nullable();
            $table->timestamp('deleted_at')->nullable();
            $table->text('ip')->nullable();
            $table->text('form_data')->nullable();
        });
    }
}
