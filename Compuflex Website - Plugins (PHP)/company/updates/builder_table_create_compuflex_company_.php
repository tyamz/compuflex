<?php namespace Compuflex\Company\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableCreateCompuflexCompany extends Migration
{
    public function up()
    {
        Schema::create('compuflex_company_', function($table)
        {
            $table->engine = 'InnoDB';
            $table->increments('id')->unsigned();
            $table->text('name');
            $table->text('description')->nullable();
        });
    }
    
    public function down()
    {
        Schema::dropIfExists('compuflex_company_');
    }
}
