<?php namespace Compuflex\Faqs\Updates;

use Schema;
use October\Rain\Database\Updates\Migration;

class BuilderTableUpdateCompuflexFaqsQuestions extends Migration
{
    public function up()
    {
        Schema::table('compuflex_faqs_questions', function($table)
        {
            $table->timestamp('created_at')->nullable();
            $table->timestamp('updated_at')->nullable();
            $table->timestamp('deleted_at')->nullable();
            $table->increments('id')->unsigned()->change();
        });
    }
    
    public function down()
    {
        Schema::table('compuflex_faqs_questions', function($table)
        {
            $table->dropColumn('created_at');
            $table->dropColumn('updated_at');
            $table->dropColumn('deleted_at');
            $table->increments('id')->unsigned(false)->change();
        });
    }
}
