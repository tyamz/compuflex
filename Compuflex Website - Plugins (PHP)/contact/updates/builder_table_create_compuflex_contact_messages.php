<?php namespace Compuflex\Contact\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableCreateCompuflexContactMessages extends Migration
{
    public function up()
    {
        Schema::create('compuflex_contact_messages', function($table)
        {
            $table->engine = 'InnoDB';
            $table->increments('id')->unsigned();
            $table->text('sender_name')->nullable();
            $table->text('sender_email')->nullable();
            $table->text('message')->nullable();
            $table->dateTime('date_sent')->nullable();
        });
    }
    
    public function down()
    {
        Schema::dropIfExists('compuflex_contact_messages');
    }
}
